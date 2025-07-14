package com.example.lutando.presentation.navigation

/**
 * Rotas de navegação do aplicativo Lutando
 */
object NavRoutes {
    const val HOME = "home"
    const val MARTIAL_ART_DETAIL = "martial_art_detail/{martialArtId}"
    const val TECHNIQUE_DETAIL = "technique_detail/{techniqueId}"
    const val TECHNIQUE_FORM = "technique_form/{martialArtId}"
    const val TECHNIQUE_EDIT = "technique_edit/{techniqueId}"

    /**
     * Gera a rota para detalhes da modalidade de arte marcial
     */
    fun martialArtDetail(martialArtId: Int): String {
        return "martial_art_detail/$martialArtId"
    }

    /**
     * Gera a rota para detalhes da técnica
     */
    fun techniqueDetail(techniqueId: Int): String {
        return "technique_detail/$techniqueId"
    }

    /**
     * Gera a rota para formulário de nova técnica
     */
    fun techniqueForm(martialArtId: Int): String {
        return "technique_form/$martialArtId"
    }

    /**
     * Gera a rota para edição de técnica existente
     */
    fun techniqueEdit(techniqueId: Int): String {
        return "technique_edit/$techniqueId"
    }
} 