package me.gr.realm

import android.app.Application
import io.realm.Realm

/**
 * Created by GR on 2017/9/11.
 */
class App: Application(){

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}