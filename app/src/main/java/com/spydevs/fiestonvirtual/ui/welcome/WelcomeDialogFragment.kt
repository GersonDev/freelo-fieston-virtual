package com.spydevs.fiestonvirtual.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.spydevs.fiestonvirtual.R
import kotlinx.android.synthetic.main.dialog_fragment_welcome.*

class WelcomeDialogFragment : DialogFragment() {

    private var title: String? = null
    private var description: String? = null
    private var subtitle: String? = null
    private var urlImage: String? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        context?.let { context ->
            ContextCompat.getColor(context, R.color.colorPrimaryDark)
        }?.let { color ->
            dialog?.window?.statusBarColor = color
        }
    }

    override fun getTheme(): Int {
        return R.style.DialogFragmentBase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            title = bundle.getString(TITLE)
            description = bundle.getString(DESCRIPTION)
            subtitle = bundle.getString(SUBTITLE)
            urlImage = bundle.getString(URL_IMAGE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcome_tv_title.text = title
        welcome_tv_description.text = description
        welcome_tv_subtitle.text = subtitle
    }

    companion object {
        const val TAG = "TAG"
        const val TITLE = "TITLE"
        const val DESCRIPTION = "DESCRIPTION"
        const val SUBTITLE = "SUBTITLE"
        const val URL_IMAGE = "URL_IMAGE"
        fun newInstance(
            title: String,
            description: String,
            subTitle: String,
            urlImage: String
        ): WelcomeDialogFragment {
            return WelcomeDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putString(DESCRIPTION, description)
                    putString(SUBTITLE, subTitle)
                    putString(URL_IMAGE, urlImage)
                }
            }
        }
    }
}