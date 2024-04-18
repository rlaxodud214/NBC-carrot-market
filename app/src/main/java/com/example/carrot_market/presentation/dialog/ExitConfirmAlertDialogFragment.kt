package com.example.carrot_market.presentation.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ExitConfirmAlertDialogFragment(
    val positiveListener: (DialogInterface, Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("종료")
            .setMessage("종료하겠습니까")
            .setNegativeButton("취소") { _: DialogInterface, _: Int -> }
            .setPositiveButton("확인") { p0, p1 ->
                positiveListener(p0, p1)
            }
            .create()

    companion object {
        const val TAG = "ExitConfirmAlertDialogFragment"
    }
}