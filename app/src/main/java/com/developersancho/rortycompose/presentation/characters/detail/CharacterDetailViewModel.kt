package com.developersancho.rortycompose.presentation.characters.detail

import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.rortycompose.domain.usecase.character.GetCharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetail: GetCharacterDetail
) : MviViewModel<BaseViewState<CharacterDetailViewState>, CharacterDetailEvent>() {

    override fun onTriggerEvent(eventType: CharacterDetailEvent) {
        when (eventType) {
            is CharacterDetailEvent.LoadDetail -> onLoadDetail(eventType.id)
        }
    }

    private fun onLoadDetail(id: Int) = safeLaunch {
        val params = GetCharacterDetail.Params(detailId = id)
        execute(getCharacterDetail(params)) { dto ->
            setState(BaseViewState.Data(CharacterDetailViewState(character = dto)))
        }
    }
}