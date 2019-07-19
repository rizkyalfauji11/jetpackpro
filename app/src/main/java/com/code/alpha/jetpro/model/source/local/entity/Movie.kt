package com.code.alpha.jetpro.model.source.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_favorite")
data class Movie(
    @ColumnInfo(name = "desc")
    @SerializedName("desc")
    var desc: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String,

    @ColumnInfo(name = "lengthOfTime")
    @SerializedName("lengthOfTime")
    var lengthOfTime: String?,

    @ColumnInfo(name = "photo")
    @SerializedName("photo")
    var photo: String?,

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    var rating: String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String?,

    @ColumnInfo(name = "year")
    @SerializedName("year")
    var year: String?,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String?,

    @ColumnInfo(name = "status")
    @SerializedName("status")
    var status: Boolean

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(desc)
        parcel.writeString(id)
        parcel.writeString(lengthOfTime)
        parcel.writeString(photo)
        parcel.writeString(rating)
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(type)
        parcel.writeValue(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}