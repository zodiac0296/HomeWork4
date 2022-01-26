package com.example.jurnal.fragments

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jurnal.R
import com.example.jurnal.models.Notes

class EdNotesFragment : Fragment() {
    var formatDate = SimpleDateFormat("dd MMMM YYYY")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editor_notes, container, false)

        val nameEd: EditText = view.findViewById(R.id.name_editor)
        val shortDescription: EditText = view.findViewById(R.id.short_description_editor)
        val detailedDescription: EditText = view.findViewById(R.id.detailed_description_editor)
        val startDateEvent: TextView = view.findViewById(R.id.start_event_editor)
        val endDateEvent: TextView = view.findViewById(R.id.end_event_editor)
        val userNotes: EditText = view.findViewById(R.id.user_notes_editor)

        val saveBtn: Button = view.findViewById(R.id.save_btn)
        val backBtn: Button = view.findViewById(R.id.back_on_main)

        val note = requireArguments().getSerializable("NOTES") as Notes

        nameEd.setText(note.name)
        shortDescription.setText(note.shortDescription)
        detailedDescription.setText(note.detailedDescription)
        startDateEvent.text = note.startDateEvent
        endDateEvent.text = note.endDateEvent
        userNotes.setText(note.userNotes)

        startDateEvent.setOnClickListener{
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    startDateEvent.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }

        endDateEvent.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    endDateEvent.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }

        saveBtn.setOnClickListener {
            for(item in notes){
                if (note.id == item.id){
                    item.name = nameEd.text.toString()
                    item.shortDescription = shortDescription.text.toString()
                    item.detailedDescription = detailedDescription.text.toString()
                    item.startDateEvent = startDateEvent.text.toString()
                    item.endDateEvent = endDateEvent.text.toString()
                    item.userNotes = userNotes.text.toString()

                    Toast.makeText(activity, "Note update successfully", Toast.LENGTH_SHORT).show()

                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.conteiner_frag, MainFragment())?.commit()

                    Log.d("@@@@", "Update notes: ${item}")
                }
            }
        }

        backBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, MainFragment())?.commit()
        }

        return view

    }
}