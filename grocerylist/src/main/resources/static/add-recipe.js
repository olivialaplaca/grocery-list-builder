const inputBoxes = document.querySelectorAll(".input-box");

const addRecipeObject = {
    "recipeName": "",
    "servings" : "",
    "ingredients": []
}

inputBoxes[0].addEventListener("change", function() {
    target = document.querySelector(".recipe-name-target");
    target.innerText = inputBoxes[0].value;
    addRecipeObject.recipeName = inputBoxes[0].value;
    inputBoxes[0].value = "";
});

inputBoxes[1].addEventListener("change", function() {
    target = document.querySelector(".servings-target");
    target.innerText = inputBoxes[1].value;
    addRecipeObject.servings = inputBoxes[1].value;
    inputBoxes[1].value = "";
});

const addIngredientButton = document.querySelector(".add-ingredient-btn");

addIngredientButton.addEventListener("click", function() {
    ingName = inputBoxes[2].value;
    ingQuant = inputBoxes[3].value;
    ingUnit = inputBoxes[4].value;
    const newIng = document.createElement("div");
    const newIngText = document.createTextNode(ingName + ", " + ingQuant + " " + ingUnit);
    newIng.appendChild(newIngText);
    const ingList = document.querySelector(".ingredient-list");
    ingList.insertAdjacentElement("beforeend", newIng);
    newIngredient = {
        "ingredientName": ingName,
        "quantity": ingQuant,
        "unitOfMeasure": ingUnit
    }
    addRecipeObject.ingredients.push(newIngredient);
    inputBoxes[2]. value = "";
    inputBoxes[3]. value = "";
    inputBoxes[4]. value = "";
})

const saveButton = document.querySelector(".save-btn");
const url = "/recipe/create-recipe";

saveButton.addEventListener("click", saveRecipe);

async function saveRecipe () {
    const result = await fetch(url, {
        method: "POST",
        body: JSON.stringify(addRecipeObject)
    })
    console.log(result);
}