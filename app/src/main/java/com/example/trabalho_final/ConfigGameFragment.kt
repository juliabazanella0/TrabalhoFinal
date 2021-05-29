package com.example.trabalho_final

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabalho_final.adapters.CategoryAdapter
import com.example.trabalho_final.dao.GameDAO
import com.example.trabalho_final.models.Category
import com.example.trabalho_final.models.Difficulty
import kotlinx.android.synthetic.main.fragment_config_game.view.*

class ConfigGameFragment : Fragment() {
    private val categoryAdapter = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_config_game, container, false)

        val selectedDifficulty = saveDifficulty()
        val selectedCategory = saveCategory()

        val difficultyAdapter = ArrayAdapter<Difficulty>(requireContext(), android.R.layout.simple_spinner_item, obterDificuldades()).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.selectedDifficulty.adapter = adapter                                       //obtendo dificuldades
        }

        if(selectedDifficulty != null) {
            view.selectedDifficulty.setSelection(difficultyAdapter.getPosition(selectedDifficulty))
        }

        categoryAdapter.setSelectedCategory(selectedCategory)

        view.listCategories.adapter = categoryAdapter
        view.listCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.btStartGame.setOnClickListener {
            saveConfigGame(view.selectedDifficulty.selectedItem as Difficulty, categoryAdapter.getSelectedCategory())
            startGame(view.selectedDifficulty.selectedItem as Difficulty, categoryAdapter.getSelectedCategory())
        }

        return view
    }

    fun saveCategory(): Category? {
        val iDcategory = requireContext().getSharedPreferences("config", Context.MODE_PRIVATE).getLong("id_category", -1)

        val nameCategory = requireContext().getSharedPreferences("config", Context.MODE_PRIVATE).getString("name_category", "")

        if(iDcategory.toInt() === -1) {
            return null
        }
        return Category(iDcategory, nameCategory!!)
    }

    fun saveDifficulty(): Difficulty? {
        val difficultyValue = requireContext().getSharedPreferences("config", Context.MODE_PRIVATE).getString("difficulty", "")

        if(difficultyValue!!.isEmpty()) {
            return null
        }

        val difficultyStringResId = resources.getIdentifier(difficultyValue, "string", requireContext().packageName)
        return Difficulty(getString(difficultyStringResId), difficultyValue)
    }

    fun saveConfigGame(selectedDifficulty: Difficulty, selectedCategory: Category?) {
        requireContext()
                .getSharedPreferences("config", Context.MODE_PRIVATE).edit().apply {
                    if(selectedCategory != null) {
                        putLong("id_category", selectedCategory.id)
                        putString("name_category", selectedCategory.name)
                    } else {
                        remove("id_category")
                        putString("name_category", "Random")
                    }
                    putString("difficulty", selectedDifficulty.value)
                    apply()
                }
    }

    fun startGame(difficulty: Difficulty, category: Category?) {
        val gameDao = GameDAO()
        gameDao.start(obterToken(), difficulty.value, category?.id.toString()) { response ->
            requireContext()
                    .getSharedPreferences("game", Context.MODE_PRIVATE)
                    .edit()
                    .apply {
                        putString("status", response.data.game.status)
                        putString("started_at", response.data.game.startedAt.toString())
                        putLong("score", response.data.game.score)

                        apply()
                    }

            goToQuestionFragment()
        }

    }

    fun obterDificuldades(): List<Difficulty> {   //strings
        val difficulties = listOf<Difficulty>(
                Difficulty(getString(R.string.easy), "easy"),
                Difficulty(getString(R.string.medium), "medium"),
                Difficulty(getString(R.string.hard), "hard")
        )
        return difficulties
    }

    fun obterToken(): String{
        val token = requireContext()
                .getSharedPreferences("login", Context.MODE_PRIVATE)
                .getString("token", "")

        return token!!
    }

    fun goToQuestionFragment(){
        findNavController().navigate(R.id.action_configGameFragment_to_questionFragment)
    }

}