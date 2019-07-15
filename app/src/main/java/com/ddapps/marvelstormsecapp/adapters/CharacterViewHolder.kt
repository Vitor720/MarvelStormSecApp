package com.ddapps.marvelstormsecapp.adapters

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.ddapps.marvelstormsecapp.data.models.Character
import com.ddapps.marvelstormsecapp.data.models.FavoriteHero
import com.ddapps.marvelstormsecapp.di.ktx.load
import com.ddapps.marvelstormsecapp.di.ktx.toTypeface
import com.ddapps.marvelstormsecapp.ui.CharacterDetailActivity
import com.ddapps.marvelstormsecapp.ui.view.BaseViewHolder
import kotlinx.android.synthetic.main.row_character.view.*
import timber.log.Timber
import android.support.v4.util.Pair as UtilPairCompat


class CharacterViewHolder(
    itemView: View

) : BaseViewHolder<Character>(itemView) {

    override fun bind(item: Character) {

        itemView.apply {
            character_name.text = item.name
            val imageUrl: String = item.thumbnail.toString()
            val charIntentInfo = FavoriteHero(item.id, item.name, item.description, imageUrl)

//            if (imageUrl.contains("image_not_available", ignoreCase = true))
//
//                else{
//                Timber.e("Vai carregar?")
//                row_background_image.load(context as Activity, imageUrl)
//            }
            row_background_image.load(context as Activity, imageUrl)

            itemView.setOnClickListener {
                Timber.e("click")
                val intent = Intent(context, CharacterDetailActivity::class.java)
                intent.putExtra(CharacterDetailActivity.EXTRA_FAVORITE_HERO, charIntentInfo)

                val activity = context as Activity

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    UtilPairCompat.create(itemView.character_name as View, item.name),
                    UtilPairCompat.create(itemView.character_name as View, item.description),
                    UtilPairCompat.create(itemView.row_background_image as View, imageUrl)
                )
                ContextCompat.startActivity(activity, intent, options.toBundle())
            }
            character_name.toTypeface("OpenSans-SemiBold")

        }
    }

}
