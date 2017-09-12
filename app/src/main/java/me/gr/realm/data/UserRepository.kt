package me.gr.realm.data

import io.realm.Realm
import io.realm.RealmResults
import me.gr.realm.entity.User

/**
 * Created by GR on 2017/9/11.
 */
class UserRepository : DataSource<User> {
    private val realm = Realm.getDefaultInstance()

    override fun findAll(): RealmResults<User> {
        return realm.where(User::class.java).findAll()
    }

    override fun save(entity: User) {
        realm.executeTransaction {
            it.copyToRealm(entity)
        }
    }

    override fun delete(entity: User): User {
        val deleteEntity=realm.copyFromRealm(entity)
        realm.executeTransaction {
            entity.deleteFromRealm()
        }
        return deleteEntity
    }

    override fun close() {
        realm.close()
    }
}