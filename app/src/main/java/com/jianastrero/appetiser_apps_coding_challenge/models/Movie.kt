package com.jianastrero.appetiser_apps_coding_challenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Extracted from the JSON return of the api
 *
 * @property artistId
 * @property artistName
 * @property artistViewUrl
 * @property artworkUrl100
 * @property artworkUrl30
 * @property artworkUrl60
 * @property collectionArtistId
 * @property collectionArtistViewUrl
 * @property collectionCensoredName
 * @property collectionExplicitness
 * @property collectionHdPrice
 * @property collectionId
 * @property collectionName
 * @property collectionPrice
 * @property collectionViewUrl
 * @property contentAdvisoryRating
 * @property copyright
 * @property country
 * @property currency
 * @property description
 * @property discCount
 * @property discNumber
 * @property hasITunesExtras
 * @property isStreamable
 * @property kind
 * @property longDescription
 * @property previewUrl
 * @property primaryGenreName
 * @property releaseDate
 * @property shortDescription
 * @property trackCensoredName
 * @property trackCount
 * @property trackExplicitness
 * @property trackHdPrice
 * @property trackHdRentalPrice
 * @property trackId
 * @property trackName
 * @property trackNumber
 * @property trackPrice
 * @property trackRentalPrice
 * @property trackTimeMillis
 * @property trackViewUrl
 * @property wrapperType
 */
@Parcelize
data class Movie(
    var artistId: Int,
    var artistName: String?,
    var artistViewUrl: String?,
    var artworkUrl100: String?,
    var artworkUrl30: String?,
    var artworkUrl60: String?,
    var collectionArtistId: Int,
    var collectionArtistViewUrl: String?,
    var collectionCensoredName: String?,
    var collectionExplicitness: String?,
    var collectionHdPrice: Double,
    var collectionId: Int,
    var collectionName: String?,
    var collectionPrice: Double,
    var collectionViewUrl: String?,
    var contentAdvisoryRating: String?,
    var copyright: String?,
    var country: String?,
    var currency: String?,
    var description: String?,
    var discCount: Int,
    var discNumber: Int,
    var hasITunesExtras: Boolean,
    var isStreamable: Boolean,
    var kind: String?,
    var longDescription: String?,
    var previewUrl: String?,
    var primaryGenreName: String?,
    var releaseDate: String?,
    var shortDescription: String?,
    var trackCensoredName: String?,
    var trackCount: Int,
    var trackExplicitness: String?,
    var trackHdPrice: Double,
    var trackHdRentalPrice: Double,
    var trackId: Int,
    var trackName: String?,
    var trackNumber: Int,
    var trackPrice: Double,
    var trackRentalPrice: Double,
    var trackTimeMillis: Int,
    var trackViewUrl: String?,
    var wrapperType: String?
) : Parcelable