package com.spydevs.fiestonvirtual.ui.main.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessageViewType
import kotlinx.android.synthetic.main.item_incoming_message.view.*
import kotlinx.android.synthetic.main.item_outgoing_message.view.*

class ChatMessagesAdapter(private val chatMessages: MutableList<ChatMessage> = mutableListOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return chatMessages[position].viewType.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder: RecyclerView.ViewHolder
        val view: View
        when(viewType) {
            ChatMessageViewType.INCOMING.value -> {
                view = inflater.inflate(R.layout.item_incoming_message, parent, false)
                viewHolder = IncomingMessageViewHolder(view)
            }
            ChatMessageViewType.OUTGOING.value -> {
                view = inflater.inflate(R.layout.item_outgoing_message, parent, false)
                viewHolder = OutgoingMessageViewHolder(view)
            }
            else -> {
                view = inflater.inflate(R.layout.item_incoming_message, parent, false)
                viewHolder = IncomingMessageViewHolder(view)
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ChatMessageViewType.INCOMING.value -> {
                (holder as IncomingMessageViewHolder).bind(chatMessages[position])
            }
            ChatMessageViewType.OUTGOING.value -> {
                (holder as OutgoingMessageViewHolder).bind(chatMessages[position])
            }
        }
    }

    inner class IncomingMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var chatMessage: ChatMessage

        fun bind(chatMessage: ChatMessage) {
            this.chatMessage = chatMessage
//            itemView.categoryForegroundImageView.setImageResource(category.image)
            itemView.chatFragment_tv_externalUserComment.text = chatMessage.messageText
//            itemView.descriptionTextView.text = category.description
//            itemView.moreDescriptionTextView.text = category.subDescription
        }
    }

    inner class OutgoingMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var chatMessage: ChatMessage

        fun bind(chatMessage: ChatMessage) {
            this.chatMessage = chatMessage
//            itemView.categoryForegroundImageView.setImageResource(category.image)
            itemView.chatFragment_tv_internalUserComment.text = chatMessage.messageText
//            itemView.descriptionTextView.text = category.description
//            itemView.moreDescriptionTextView.text = category.subDescription
        }
    }

    fun addAllData(chatMessages: List<ChatMessage>) {
        this.chatMessages.addAll(chatMessages)
        notifyDataSetChanged()
    }

    fun addData(chatMessage: ChatMessage) {
        this.chatMessages.add(chatMessage)
        notifyDataSetChanged()
    }

}