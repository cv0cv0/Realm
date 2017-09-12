package me.gr.realm.domain

import io.realm.OrderedRealmCollection
import me.gr.realm.base.BasePresenter
import me.gr.realm.base.BaseView
import me.gr.realm.entity.User

/**
 * Created by GR on 2017/9/11.
 */
interface MainContract {

    interface View : BaseView<Presenter> {

        fun showUsers(data: OrderedRealmCollection<User>)

        fun showAddUser()

        fun showSnackbar(view: android.view.View,name:String)
    }

    interface Presenter : BasePresenter {

        fun clickFloatButton()

        fun addUser(user: User)

        fun deleteUser(v: android.view.View, user: User?)

        fun reversalDelete()
    }
}