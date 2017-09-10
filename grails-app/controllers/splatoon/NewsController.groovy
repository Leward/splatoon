package splatoon

import grails.plugin.springsecurity.annotation.Secured
import  grails.gorm.transactions.Transactional

class NewsController {

    def list() {
        def newsPerPage = 12
        def page = params.getLong('page', 1)
        def offset = (page - 1) * newsPerPage
        def newsList = News.findAll([offset: offset, max: newsPerPage, sort: 'date', order: 'desc'])
        def nbPages = Math.ceil(News.count()/ newsPerPage)
        render(view: 'list', model: [newsList: newsList, page: page, nbPages: nbPages])
    }

    def show(News news) {
        render(view: 'show', model: [news:news])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    def admin_index() {
        render(view: "admin_index", model: [
                newsList: News.findAll(sort: 'date', order: 'desc')
        ])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    def admin_show(News news) {
        render(view: "admin_show", model: [news: news])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    @Transactional
    def admin_create() {
        def news = new News(params)
        if (request.isPost() && news.validate()) {
            news.save(flush: true)
            redirect(mapping: 'admin_news_show', id: news.id)
        }
        render(view: "admin_create", model: [news: news])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    @Transactional
    def admin_update(News news) {
        if (request.isPost() && news.validate()) {
            news.save(flush: true)
            redirect(mapping: 'admin_news_show', id: news.id)
        }
        render(view: "admin_edit", model: [news: news])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    @Transactional
    def admin_delete(News news) {
        if (request.method == 'DELETE') {
            news.delete(flush: true)
        }
        redirect(mapping: 'admin_news')
    }
}
