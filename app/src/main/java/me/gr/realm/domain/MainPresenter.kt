package me.gr.realm.domain

import android.view.View
import me.gr.realm.data.UserRepository
import me.gr.realm.entity.User

/**
 * Created by GR on 2017/9/11.
 */
class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val repository = UserRepository()

    private var removedUser: User? = null

    override fun start() {
        view.showUsers(repository.findAll())
    }

    override fun clickFloatButton() {
        view.showAddUser()
    }

    override fun addUser(user: User) {
        repository.save(user)
    }

    override fun release() {
        repository.close()
    }

    override fun deleteUser(v: View, user: User?) {
        if (user != null) {
            removedUser = repository.delete(user)
            view.showSnackbar(v, removedUser!!.name)
        }
    }

    override fun reversalDelete() {
        repository.save(removedUser!!)
    }
}