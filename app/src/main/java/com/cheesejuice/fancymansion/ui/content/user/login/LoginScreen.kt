package com.cheesejuice.fancymansion.ui.content.user.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.UiState
import com.cheesejuice.fancymansion.ui.common.component.BasicButton
import com.cheesejuice.fancymansion.ui.common.frame.BaseScreen
import com.cheesejuice.fancymansion.ui.theme.colorScheme
import com.cheesejuice.fancymansion.ui.theme.typography

@Composable
fun LoginScreenSetup(
    navController : NavController,
    viewModel : LoginViewModel = hiltViewModel()
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result : ActivityResult ->
        viewModel.resultForGoogleSignInDialog(result)
    }

    val uiState by viewModel.uiState.collectAsState()
    LoginScreenFrame(
        uiState = uiState,
        onClickGoogleSigning = {
            launcher.launch(viewModel.getSignIntent())
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        LoginScreenFrame()
    }
}

@Composable
fun LoginScreenFrame(
    uiState: UiState = UiState.Loaded(null),
    isGoogleValid : Boolean = true,
    onClickGoogleSigning : () -> Unit = {},
    onClickStartLocal : () -> Unit = {}
){
    BaseScreen(
        uiState = uiState,
    ) {
        LoginScreenContent(
            isGoogleValid = isGoogleValid,
            onClickGoogleSigning = onClickGoogleSigning,
            onClickStartLocal = onClickStartLocal
        )
    }
}

@Composable
fun LoginScreenContent(
    isGoogleValid : Boolean = true,
    onClickGoogleSigning : () -> Unit = {},
    onClickStartLocal : () -> Unit = {}
) {
    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(0.3f))

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.large)
            )
            Spacer(modifier = Modifier.weight(1f))

            BasicButton(
                text = stringResource(id = R.string.login),
                isClickable = isGoogleValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .clip(MaterialTheme.shapes.small),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                onClickGoogleSigning()
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}