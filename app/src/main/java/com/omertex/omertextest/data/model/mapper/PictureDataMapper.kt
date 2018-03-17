package com.omertex.omertextest.data.model.mapper

import com.omertex.omertextest.data.model.entity.Picture
import com.omertex.omertextest.data.model.responce.PictureResponse

class PictureDataMapper {

    fun map(pictureResponse: PictureResponse): Picture {
        return Picture(
                pictureResponse.format,
                pictureResponse.width,
                pictureResponse.height,
                pictureResponse.filename,
                pictureResponse.id,
                pictureResponse.author,
                pictureResponse.author_url,
                pictureResponse.post_url
        )
    }
}