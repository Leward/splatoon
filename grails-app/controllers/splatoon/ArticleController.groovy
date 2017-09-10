package splatoon

import grails.plugin.springsecurity.annotation.Secured
import  grails.gorm.transactions.Transactional

class ArticleController {

    def magazine() {
        Map<ArticleCategory, List<Article>> categoryMap = [:]
        ArticleCategory.list().each {
            categoryMap[it] = Article.findAllByCategory(it, [sort: 'date', order: 'desc', max: 4])
        }
        render(view: 'magazine', model: [categories: categoryMap])
    }

    def show(Article article) {
        render(view: 'show', model: [article: article])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    def admin_index() {
        render(view: "admin_index", model: [
                articleList: Article.findAll(sort: 'date', order: 'desc')
        ])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    def admin_show(Article article) {
        render(view: "admin_show", model: [article: article])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    @Transactional
    def admin_create() {
        def article = new Article(params)
        if (request.isPost() && article.validate()) {
            article.save(flush: true)
            redirect(mapping: 'admin_article_show', id: article.id)
        }
        render(view: "admin_create", model: [article: article])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    @Transactional
    def admin_update(Article article) {
        if (request.isPost() && article.validate()) {
            article.save(flush: true)
            redirect(mapping: 'admin_article_show', id: article.id)
        }
        render(view: "admin_edit", model: [article: article])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDITOR'])
    @Transactional
    def admin_delete(Article article) {
        if (request.method == 'DELETE') {
            article.delete(flush: true)
        }
        redirect(mapping: 'admin_article')
    }

}
