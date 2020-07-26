package com.spydevs.fiestonvirtual.domain.models.comment

data class AddCommentRequest(var idPost: Int, var idUserComment: Int, var comment: String)