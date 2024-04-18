package com.example.carrot_market.presentation.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.carrot_market.R

class RemoveItemConfirmAlertDialogFragment(
    val positiveListener: (DialogInterface, Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setIcon(R.drawable.ic_bubble_chat)
            .setTitle("상품 삭제")
            .setMessage("상품을 정말로 삭제하겠시습니까?")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("확인") { p0, p1 ->
                positiveListener(p0, p1)
            }
            .create()

    companion object {
        const val TAG = "RemoveItemConfirmAlertDialogFragment"
    }
}