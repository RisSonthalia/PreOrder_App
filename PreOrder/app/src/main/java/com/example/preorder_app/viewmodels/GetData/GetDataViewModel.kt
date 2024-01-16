package com.example.preorder_app.viewmodels.GetData

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GetDataViewModel:ViewModel() {
    var inload= mutableStateOf(false)
    fun retrieveData(
        id: String,
        context: Context,
        data: (Items) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        inload.value=true
        val fireStoreRef = Firebase.firestore
            .collection("Items")
            .document(id)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    // for getting single or particular document
                    if (it.exists()) {
                        val name=it.data!!["Name"].toString()
                        val price=it.data!!["Price"].toString()
                        val id = it.data!!["id"].toString()
                        inload.value=false
                       data(Items(
                            Name =name,
                            Price = price,
                            id =id
                        ))
                    } else {
                        inload.value=false
                        Toast.makeText(context, "No  Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
