package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User {

	transient SpringSecurityService springSecurityService

	String username
	String password
	String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
		email blank: false, email: true, unique: true
	}

	static mapping = {
		password column: '`password`'
	}

	boolean hasRole(String role) {
		return authorities.any { it.authority == role }
	}

	@Override
	String toString() {
		return username
	}

	@Override
	boolean equals(Object o) {
		if(o == null || !(o instanceof User)) {
			return false
		}
		def otherUser = o as User
		return id != null && id == otherUser.id
	}
}
