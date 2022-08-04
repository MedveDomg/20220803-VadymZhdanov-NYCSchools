package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist.SatScoreModel

class SatScoreDialogDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val satScoreModel = arguments?.getSerializable(SCHOOL_KEY) as? SatScoreModel
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("SAT scores")
            .setMessage(
                "Math: ${satScoreModel?.scoreMath}" +
                        "\n" +
                        "Reading: ${satScoreModel?.scoreReading}" +
                        "\n" +
                        "Writing: ${satScoreModel?.scoreWriting}"
            )
            .setPositiveButton("Ok") { _, _ ->
                dismiss()
            }
            .setCancelable(false)
            .create()
    }

    companion object {
        const val SCHOOL_KEY = "SCHOOL_KEY"

        fun newInstance(model: SatScoreModel? = null): SatScoreDialogDialog {
            val dialog = SatScoreDialogDialog()
            dialog.arguments = bundleOf(SCHOOL_KEY to model)
            return dialog
        }
    }
}