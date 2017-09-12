package me.gr.realm.data

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Created by GR on 2017/9/11.
 */
interface DataSource<T : RealmModel> {

    fun findAll(): RealmResults<T>

    fun save(entity: T)

    fun delete(entity: T):T

    fun close()
}