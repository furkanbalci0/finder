package com.furkanbalci.finder.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

//Progress scenario for 10 questions
class Playbook(val surveys: Set<Survey>) {

    var completed = false

}