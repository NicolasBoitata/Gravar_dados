package com.test.gravardados.ui

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.test.gravardados.R
//import kotlinx.android.synthetic.main.fragment_authors.*


class AuthorsFragment : Fragment() {

    lateinit var button_add: Button/* = view!!.findViewById(R.id.button_add)*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_add.setOnClickListener {
            AddAuthorDiaIogFragment()
                .show(childFragmentManager, "")
        }
    }
}
