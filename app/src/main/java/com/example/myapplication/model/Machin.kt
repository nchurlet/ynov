package com.example.myapplication.model

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon

/**
 * La classe Machin n'a pas d'utilité dans un projet réel. Il ne sert qu'à tirer profit du profit du MachinAdapter.
 * Dans un projet normal, il faudra créer un adapter par item nécessaire. Pour éviter la construction d'objets juste
 * pour le recyclerView.
 * C'est d'autant plus inutile et inefficace que l'adapter est justement fait pour faire ce travail.
 */
data class Machin (
    val main: String,
    val description: String
)
