package com.cheesejuice.core.ui.component

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.cheesejuice.core.ui.R
import java.io.File

@Composable
fun BookImage(
    modifier : Modifier = Modifier,
    image : Any?
) {
    val painter = if ((image is File && image.exists()) || (image is Int)) {
        LocalContext.current.let {
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(it).data(
                    data = image
                ).apply(block = { size(Size.ORIGINAL) }).build(),

                imageLoader = ImageLoader.Builder(it)
                    .components {
                        if (Build.VERSION.SDK_INT >= 28) {
                            add(ImageDecoderDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }.build()
            )
        }
    } else {
        painterResource(id = R.drawable.il_default_image)
    }

    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        painter = painter
    )
}