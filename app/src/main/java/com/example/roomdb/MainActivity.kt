package com.example.roomdb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.roomdb.Room.MyDatabase
import com.example.roomdb.Room.User
import com.example.roomdb.ui.theme.RoomDbTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDbTheme {
                // A surface container using the 'background' color from the theme


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val context = LocalContext.current
                    val database = Room.databaseBuilder(
                        context,
                        MyDatabase::class.java,
                        "MyDatabase"
                    ).allowMainThreadQueries().build()
                    val userDao = database.userDao()
                    var data by remember { mutableStateOf("") }
                    var username by remember { mutableStateOf("") }
                    var age by remember { mutableStateOf("") }
                  //  var id by remember { mutableStateOf("") }

            Column (modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text(
                    text = "Username"
                )})


                OutlinedTextField(value = age, onValueChange = {age = it},
                    label = { Text(text = "Age")}
                     )


               // OutlinedTextField(value = id, onValueChange = {id = it},
              //      label = { Text(text = "ID")}
              //  )

                Button(onClick = {
                    //
                    val user = User(  name = username, age = age.toInt())
                    val list = mutableListOf<User>()

                    userDao.addUser(user = user)
                    Toast.makeText(context, "User Added", Toast.LENGTH_LONG).show()

                }

                ) {
                    Text(text = "Insert user")
                }



                Button(onClick = { data = userDao.getAllUsers().joinToString(separator = "\n") }

                ) {
                    Text(text = "Retrieve Data")
                }







                Button(onClick = {
                   // id = id.toInt()
                    val user = User(  name = username, age = age.toInt())
                  //  val list = mutableListOf<User>()

                    userDao.deleteAllUsers()
                    Toast.makeText(context, "Users Deleted", Toast.LENGTH_LONG).show()
                }

                ) {
                    Text(text = "Delete All Users")
                }



                Text(text = data)
                
                
            }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDbTheme {
        Greeting("Android")
    }
}