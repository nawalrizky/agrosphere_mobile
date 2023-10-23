package com.olivia.plant.utils

import android.text.InputType
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


fun validateEditText(item: List<EditText>): Boolean {
    var data = true
    item.forEach {
        if (!it.validate() && data) {
            data = false
        }
    }
    return data
}

fun liveValidate(item: List<EditText>) {
    item.forEach {
        it.addTextChangedListener { _ ->
            it.validate()
        }
    }
}


private fun EditText.validate(): Boolean {
    val type = this.inputType
    when {
        this.itText().isEmpty() -> {
            formUnValidate(this, "Field tidak boleh kosong")
            return false
        }

        type == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS + 1 -> {
            return if (this.isValidEmail()) {
                formValidate(this)
                true
            } else {
                formUnValidate(this, "Email tidak valid")
                false
            }
        }

        type == 129 || type == InputType.TYPE_TEXT_VARIATION_PASSWORD -> {
            return if (this.itText().length >= 3) {
                formValidate(this)
                true
            } else {
                formUnValidate(this, "Password minimal 3 karakter")
                false
            }
        }

        else -> {
            formValidate(this)
            return true
        }
    }

}

private fun formValidate(view: View) {
    if (view is TextInputLayout) {
        view.error = null
        view.isErrorEnabled = false
    } else if (view.parent.parent is TextInputLayout) {
        (view.parent.parent as TextInputLayout).error = null
        (view.parent.parent as TextInputLayout).isErrorEnabled = false
    }
}

private fun formUnValidate(view: View, errorMessage: String) {
    if (view.parent.parent is TextInputLayout) {
        (view.parent.parent as TextInputLayout).error = errorMessage
    } else {
        (view as TextInputLayout).error = errorMessage
    }
}

private fun EditText.itText(): String {
    return this.text.toString()
}

private fun TextInputEditText.itText(): String {
    return this.text.toString()
}

fun EditText.liveValidate() {
    this.addTextChangedListener {
        this.validate()
    }
}


private fun EditText.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this.text) && Patterns.EMAIL_ADDRESS.matcher(this.text).matches()
}
