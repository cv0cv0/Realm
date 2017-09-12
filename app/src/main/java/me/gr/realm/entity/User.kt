package me.gr.realm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by GR on 2017/9/11.
 */
open class User(
        var name: String = "",
        var age: Int = 0,
        @PrimaryKey var id: String = UUID.randomUUID().toString()
) : RealmObject()