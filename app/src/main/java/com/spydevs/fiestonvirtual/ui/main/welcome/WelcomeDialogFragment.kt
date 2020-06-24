package com.spydevs.fiestonvirtual.ui.main.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import kotlinx.android.synthetic.main.dialog_fragment_welcome.*

class WelcomeDialogFragment : DialogFragment() {

    private var title: String? = null
    private var description: String? = null
    private var subtitle: String? = null
    private var imageUrl: String? = null

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
            this.title = bundle.getString(TITLE)
            this.description = bundle.getString(DESCRIPTION)
            this.subtitle = bundle.getString(SUBTITLE)
            this.imageUrl = bundle.getString(IMAGE_URL)
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
        //TODO add refactor...
        welcome_tv_title.text = title
        welcome_tv_description.text = description
        welcome_tv_subtitle.text = subtitle
        imageUrl?.let {
            welcome_iv_logo2.loadUrl(it)
        }
        welcome_tv_howFunctionality.setOnClickListener { dismiss() }
        welcome_iv_close.setOnClickListener { dismiss() }
    }

    companion object {
        const val TAG = "TAG"
        const val TITLE = "TITLE"
        const val DESCRIPTION = "DESCRIPTION"
        const val SUBTITLE = "SUBTITLE"
        const val IMAGE_URL = "IMAGE_URL"

        fun newInstance(
            title: String?,
            description: String?,
            subTitle: String?,
            urlImage: String?
        ): WelcomeDialogFragment {
            return WelcomeDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putString(DESCRIPTION, description)
                    putString(SUBTITLE, subTitle)
                    putString(IMAGE_URL, urlImage)
                }
            }
        }
    }
}