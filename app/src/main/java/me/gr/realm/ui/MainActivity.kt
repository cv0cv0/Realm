package me.gr.realm.ui

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import io.realm.OrderedRealmCollection
import kotlinx.android.synthetic.main.activity_main.*
import me.gr.realm.R
import me.gr.realm.adapter.MainAdapter
import me.gr.realm.domain.MainContract
import me.gr.realm.domain.MainPresenter
import me.gr.realm.entity.User
import me.gr.realm.ui.widget.AddDialog
import me.gr.realm.util.SwipeRemoveCallback

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var adapter: MainAdapter
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        presenter.start()
        float_button.setOnClickListener {
            presenter.clickFloatButton()
        }
    }

    override fun showUsers(data: OrderedRealmCollection<User>) {
        adapter = MainAdapter(data)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        val removeCallback = SwipeRemoveCallback()
        removeCallback.setOnItemRemoveListener { view, position ->
            presenter.deleteUser(view, adapter.data?.get(position))
        }
        ItemTouchHelper(removeCallback).attachToRecyclerView(recycler_view)
    }

    override fun showSnackbar(view: View, name: String) {
        Snackbar.make(view, "$name  已删除", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.parseColor("#eeff41"))
                .setAction("撤消", {
                    presenter.reversalDelete()
                })
                .show()
    }

    override fun showAddUser() {
        AddDialog()
                .setInputCompleteListener { name, age ->
                    presenter.addUser(User(name, age))
                }
                .show(supportFragmentManager, "add")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.release()
    }
}
