package com.cheesejuice.fancymansion.ui.common.component

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.cheesejuice.fancymansion.R
import java.io.File

@Composable
fun BookImage(
    modifier : Modifier = Modifier,
    imageFile : File,
    testResourceId : Int? = null
) {
    Image(
        modifier = modifier,
        contentDescription = null,
        painter =
        if (!imageFile.exists() && testResourceId == null) {
            painterResource(id = R.drawable.ic_launcher_background)
        } else {
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }.build()
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(context).data(
                    data = if (imageFile.exists()) {
                        imageFile
                    } else {
                        testResourceId
                    }
                ).apply(block = { size(Size.ORIGINAL) }).build(),
                imageLoader = imageLoader
            )
        }
    )
}