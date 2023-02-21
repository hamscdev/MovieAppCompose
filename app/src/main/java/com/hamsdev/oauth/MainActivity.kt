package com.hamsdev.oauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hamsdev.oauth.ui.theme.OAuthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginView()
                }
            }
        }
    }
}


@Composable
fun LoginView() {

    var userName by remember { mutableStateOf("") }
    var passwordUser by remember { mutableStateOf("") }
    var visibilityPassword by remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        var (imageLogin, userText, passText, buttonText) = createRefs()

        Image(painter = painterResource(id = R.drawable.undraw_welcome_cats_thqn),
            contentDescription = "LoginImage", modifier = Modifier.constrainAs(imageLogin){
            centerTo(parent)
                bottom.linkTo(userText.top, margin = 18.dp)
            })

       TextField(value = userName,
           onValueChange = {userName = it },
           modifier = Modifier
               .constrainAs(userText) {
                   top.linkTo(imageLogin.bottom, margin = 25.dp)
                   centerTo(parent)
               }
               .height(50.dp), label = { Text("Correo electronico")},
           leadingIcon = { Icon(
               imageVector = Icons.Filled.MailOutline,
               contentDescription = "email icon"
           )})


        TextField(value = passwordUser, onValueChange = {passwordUser = it}, modifier = Modifier
            .constrainAs(passText) {
                top.linkTo(userText.bottom, margin = 20.dp)
                centerHorizontallyTo(userText)
            }
            .height(50.dp),
            label = { Text("Contraseña")} ,
            trailingIcon = {
             val image = if(visibilityPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
             IconButton(onClick = {  visibilityPassword = !visibilityPassword}) {
                 Icon(imageVector = image,
                     contentDescription = "IconView password")
             }
        },
            visualTransformation = if(visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
            keyboardType =  KeyboardType.Password
        ),
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password" )}
        )


        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(buttonText) {

            centerHorizontallyTo(passText)
            top.linkTo(passText.bottom, margin = 15.dp)
        }.height(50.dp).fillMaxWidth(0.4f)) {
            Text("Ingresar")
        }



    }



}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OAuthTheme {
      LoginView()
    }
}