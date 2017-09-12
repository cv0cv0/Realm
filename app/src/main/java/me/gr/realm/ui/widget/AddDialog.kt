package me.gr.realm.ui.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import me.gr.realm.R

/**
 * Created by GR on 2017/9/11.
 */
class AddDialog : DialogFragment() {
    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var ageInputLayout: TextInputLayout
    private lateinit var nameEdit: EditText
    private lateinit var ageEdit: EditText

    private lateinit var alertDialog: AlertDialog

    private var onInputComplete: ((name: String, age: Int) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity.layoutInflater.inflate(R.layout.dialog_add, null)
        initView(view)
        alertDialog = AlertDialog.Builder(activity)
                .setView(view)
                .setPositiveButton("确定", { _, _ ->
                    val name = nameEdit.text.toString()
                    val age = ageEdit.text.toString()
                    if (checkNotEmpty(name, age)) {
                        setDialogCancelable(true)
                        onInputComplete?.invoke(name, age.toInt())
                    } else {
                        setDialogCancelable(false)
                    }
                })
                .create()
        return alertDialog
    }

    private fun setDialogCancelable(cancelable: Boolean) {
        val field = alertDialog.javaClass.superclass.superclass.getDeclaredField("mShowing")
        field.isAccessible = true
        field.set(alertDialog, cancelable)
        if (cancelable) alertDialog.dismiss()
    }

    private fun initView(view: View) {
        nameInputLayout = view.findViewById(R.id.name_input_layout)
        ageInputLayout = view.findViewById(R.id.age_input_layout)
        nameEdit = view.findViewById(R.id.name_edit)
        ageEdit = view.findViewById(R.id.age_edit)

        nameEdit.post {
            val imm = nameEdit.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(nameEdit,InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun checkNotEmpty(name: String, age: String): Boolean {
        nameInputLayout.isErrorEnabled = false
        ageInputLayout.isErrorEnabled = false
        if (TextUtils.isEmpty(name)) {
            nameInputLayout.error = "名字不能为空"
            nameInputLayout.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(age)) {
            ageInputLayout.error = "年龄不能为空"
            ageInputLayout.requestFocus()
            return false
        }
        return true
    }

    fun setInputCompleteListener(listener: (name: String, age: Int) -> Unit): AddDialog {
        onInputComplete = listener
        return this
    }
}