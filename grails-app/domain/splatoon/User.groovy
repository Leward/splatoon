package splatoon

import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.time.LocalDate

@EqualsAndHashCode(includes='username')
class User {

	transient SpringSecurityService springSecurityService

	String username
	String password
	String email

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	PlayerProfile playerProfile = new PlayerProfile(user: this)

	static hasMany = [
			tournamentOrganizers: TournamentOrganizer,
			adReplies: AdReply,
			recruitingAds: RecruitingAd,
			teams: Team
	]

	static hasOne = ['playerProfile': PlayerProfile]

	static belongsTo = TournamentOrganizer

	Set<Role> getAuthorities() {
		def roles = UserRole.findAllByUser(this)*.role
		if(!tournamentOrganizers.empty) {
			roles.add(Role.findByAuthority(Role.ROLE_TO))
		}
		return roles
	}

	Set<Role> getRoles() {
		return getAuthorities()
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	def beforeDelete() {
		UserRole.removeAll(this)
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
		email blank: false, email: true, unique: true
		playerProfile nullable: true
	}

	static mapping = {
		table '`user`' // backticks mandatory as in postgresql 'user' is a reserved keyword
		password column: '`password`' // backticks mandatory as in postgresql/mysql 'password' is a reserved keyword
		adReplies lazy: true
		recruitingAds lazy: true
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

	boolean canManage(TournamentOrganizer tournamentOrganizer) {
		return hasRole(Role.ROLE_ADMIN) || tournamentOrganizers.contains(tournamentOrganizer)
	}

//	void setNintendoId(String nintendoId) {
//		playerProfile.nintendoId = nintendoId
//	}
//
//	String getNintendoId() {
//		return playerProfile.nintendoId
//	}

	PlayerProfile getPlayerProfile() {
		return playerProfile ?: new PlayerProfile()
	}
}
