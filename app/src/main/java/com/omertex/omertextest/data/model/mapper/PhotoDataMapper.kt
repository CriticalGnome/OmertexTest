package com.omertex.omertextest.data.model.mapper

import com.omertex.omertextest.data.model.entity.Photo
import com.omertex.omertextest.data.model.responce.FlickrResponse
import com.omertex.omertextest.data.model.responce.FlickrSizes

class PhotoDataMapper {

    fun map(photo: FlickrResponse.Photo): Photo {
        return Photo(
                photo.id,
                photo.owner,
                photo.secret,
                photo.server,
                photo.farm,
                photo.title,
                photo.ispublic,
                photo.isfriend,
                photo.isfamily
        )
    }

    fun map(size: FlickrSizes.Size): Photo.Size {
        return Photo.Size(
                size.label,
                size.width,
                size.height,
                size.source,
                size.url,
                size.media
        )
    }

}