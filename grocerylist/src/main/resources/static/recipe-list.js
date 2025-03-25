//make a call to retrieve recipes
const getRecipeListUrl = "http://localhost:8080/recipe/all";

async function getRecipes(url) {
    const promise = await fetch(url);
    const result = await promise.json();
    const table = document.querySelector(".list-table")
    result.forEach(element => {
        let recipeRow = document.createElement("tr");
        recipeRow = table.insertAdjacentElement("beforeend", recipeRow);
        let recipe = document.createElement("td");
        recipe.innerText = element.name;
        recipeRow.insertAdjacentElement("beforeend", recipe);
    });
}

getRecipes(getRecipeListUrl);
//for each recipe, get the recipe name
//then create a new table row and td element and add the recipe name
