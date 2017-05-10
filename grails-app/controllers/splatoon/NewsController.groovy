package splatoon

import grails.transaction.Transactional

class NewsController {

    def admin_index() {
        render(view: "admin_index", model: [
                newsList: News.findAll(sort: 'date', order: 'desc')
        ])
    }

    def admin_show(News news) {
        render(view: "admin_show", model: [news: news])
    }

    @Transactional
    def admin_create() {
        def news = new News(params)
        if (request.isPost() && news.validate()) {
            news.save(flush: true)
            redirect(mapping: 'admin_news_show', id: news.id)
        }
        render(view: "admin_create", model: [news: news])
    }

    @Transactional
    def admin_update(News news) {
        if (request.isPost() && news.validate()) {
            news.save(flush: true)
            redirect(mapping: 'admin_news_show', id: news.id)
        }
        render(view: "admin_edit", model: [news: news])
    }

    @Transactional
    def admin_delete(News news) {
        if (request.method == 'DELETE') {
            news.delete(flush: true)
        }
        redirect(mapping: 'admin_news')
    }
}
