package com.example.easyplates

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipe_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val foodImageView = findViewById<ImageView>(R.id.foodImageView)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val ingredientsLayout = findViewById<LinearLayout>(R.id.ingredientsLayout)
        val stepsLayout = findViewById<LinearLayout>(R.id.stepsLayout)

        val userIcon = findViewById<ImageView>(R.id.userImageButton)

        userIcon.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }

        val homeIcon = findViewById<ImageView>(R.id.homeImageButton)

        homeIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val infoImageButton: ImageButton = findViewById(R.id.infoImageButton)
        infoImageButton.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_instructions_popup, null)

            val width = 1000
            val height = 1400

            val i = PopupWindow(popupView, width, height, true)
            i.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val closeButton = popupView.findViewById<Button>(R.id.closeButton)
            closeButton.setOnClickListener{
                i.dismiss()
            }
        }

        val name = intent.getStringExtra("recipeName")

        titleTextView.text = name
        foodImageView.setImageResource(getRecipeImage(name))

        val recipeData = getRecipeData(name)

        // Add ingredients as checkboxes
        for (ingredient in recipeData.ingredients) {
            val checkBox = CheckBox(this).apply{
                textSize = 20f
                setPadding(20, 0, 20, 40)
                gravity = Gravity.TOP
            }
            checkBox.text = ingredient
            ingredientsLayout.addView(checkBox)
        }

        // Add steps as checkboxes
        for (step in recipeData.steps) {
            val checkBox = CheckBox(this).apply {
                textSize = 20f
                setPadding(20, 0, 20, 40)
                gravity = Gravity.TOP
            }
            checkBox.text = step
            stepsLayout.addView(checkBox)
        }

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    data class Recipe(val ingredients: List<String>, val steps: List<String>)

    private fun getRecipeData(name: String?): Recipe {
        return when (name) {
            "Iced Matcha Latte" -> Recipe(
                ingredients = listOf("1 tsp matcha powder", "1 cup milk", "1 cup ice"),
                steps = listOf("Mix matcha with water", "Add milk", "Pour over ice")
            )

            "Creamy Garlic Chicken" -> Recipe(
                ingredients = listOf(
                    "2 boneless,skinless chicken breasts (about 1.3 lbs. total)",
                    "1/2 tsp Italian seasoning",
                    "1/2 tsp salt",
                    "1/4 tsp freshly cracked black pepper",
                    "1/4 cup all-purpose flour",
                    "2 Tbsp olive oil",
                    "2 Tbsp butter, divided",
                    "1 whole garlic bulb (about 8-9 garlic cloves)",
                    "1 cup chicken broth",
                    "3/4 cup heavy cream",
                    "3 cloves garlic",
                    "1/2 tsp garlic powder",
                    "3 cloves garlic",
                    "Salt and Pepper to taste"
                ),
                steps = listOf(
                    "Using a sharp knife, carefully fillet each chicken breast into two thinner cutlets (or use thin-cut chicken breasts).",
                    "Season each breast with Italian seasoning, salt, and black pepper. Then sprinkle the flour over both sides of the chicken breasts and rub it in until the chicken is evenly coated.",
                    "Heat a large skillet over medium heat and add the olive oil and 1 Tbsp of butter. Once the skillet is hot, add the chicken and cook on each side until golden brown and cooked through (about 4 minutes per side). Remove the cooked chicken to a clean plate and cover to keep warm.",
                    "While the chicken is cooking, peel the garlic cloves and then carefully smash them with the side of a large chef's knife by pressing down on the side of the knife with the heel of your hand.",
                    "Once the chicken is removed, to the same skillet add the remaining 1 Tbsp of butter, then add the garlic cloves. Turn the heat down to medium-low and sauté the garlic for 3 minutes or until lightly browned and fragrant. Make sure to stir the garlic frequently to prevent it from burning.",
                    "Next pour the chicken broth and heavy cream into the skillet, then add the garlic powder. Stir and scrape up any brown bits from the bottom of the skillet. Allow the sauce to simmer in the skillet for 8-10 minutes, or until it has reduced and thickened by about 50%. Taste the sauce at this point and add salt and pepper if needed (I added about ⅛ tsp of salt and ⅛ tsp of black pepper).",
                    " Finally, return the cooked chicken breasts to the skillet and spoon the creamy sauce over top. Allow the chicken to heat through. Serve with fresh chopped parsley (optional), and enjoy!"
                )
            )

            "Filipino Chicken Adobo" -> Recipe(
                ingredients = listOf(
                    "2 lbs chicken",
                    "3 pieces dried bay leaves",
                    "4 tablespoons soy sauce",
                    "6 tablespoons white vinegar",
                    "5 cloves garlic",
                    "1 1/2 cups water",
                    "3 tablespoons cooking oil",
                    "1 teaspoon sugar",
                    "▢1/4 teaspoon salt",
                    "1 teaspoon whole peppercorn"
                ),
                steps = listOf(
                    "Combine chicken, soy sauce, and garlic in a large bowl. Mix well. Marinate the chicken for at least 1 hour. Note: the longer the time, the better",
                    "Heat a cooking pot. Pour cooking oil.",
                    "When the oil is hot enough, pan-fry the marinated chicken for 2 minutes per side.",
                    "Pour-in the remaining marinade, including garlic. Add water. Bring to a boil",
                    "Add dried bay leaves and whole peppercorn. Simmer for 30 minutes or until the chicken gets tender",
                    "Add vinegar. Stir and cook for 10 minutes.",
                    "Put-in the sugar, and salt. Stir and turn the heat off.Serve hot. Share and Enjoy!"
                )
            )

            "French Macarons" -> Recipe(
                ingredients = listOf(
                    "3 large eggs",
                    "140 g almond flour",
                    "90 g granulated sugar",
                    "130 g powdered sugar",
                    "1 tsp vanilla ",
                    "1/4 tsp cream of tartar",
                    "1 cup unsalted butter softened",
                    "5 egg yolks",
                    "1/2 cup granulated sugar",
                    "1 tsp vanilla",
                    "3 tbsp water",
                    "1 pinch salt"
                ),
                steps = listOf(
                    "Sift the confectioners sugar and almond flour into a bowl.",
                    "Add the room temperature egg whites into a very clean bowl.",
                    "Using an electric mixer, whisk egg whites. Once they begin to foam add the cream of tartar and then SLOWLY add the granulated sugar.",
                    "Add the food coloring (if desired) and vanilla then mix in. Continue to beat until stiff peaks form.",
                    "Begin folding in the 1/3 of the dry ingredients.",
                    "Be careful to add the remaining dry ingredients and fold gently.",
                    "The final mixture should look like flowing lava, and be able to fall into a figure eight without breaking. Spoon into a piping bag with a medium round piping tip and you’re ready to start piping.",
                    "Pipe one inch dollops onto a baking sheet lined with parchment paper (this should be glued down with dabs of batter). Tap on counter several times to release air bubbles. Allow to sit for about 40 minutes before placing in oven. ",
                    "Bake at 300F for 12-15 minutes, rotate tray after 7 minutes. Allow to cool completely before removing from baking sheet. ",
                    "Combine sugar and water in medium saucepan. Heat over low heat while stirring until sugar dissolves. Increase heat to medium- high and bring to a boil",
                    "Put egg yolks in a stand-mixer fitted with a whisk attachment and beat until thick and foamy.",
                    "Cook the sugar and water syrup until it reaches 240 degrees F. Immediately remove from heat. With mixer running, SLOWLY drizzle hot syrup into bowl with yolks.",
                    "Continue mixing until the bottom of the bowl is cool to the touch and the yolk mixture has cooled to room temperature.",
                    "Add in butter one cube at a time allowing each piece to incorporate before adding the next. Add vanilla and salt. Continue mixing until buttercream is smooth and creamy. (About 5-6 minutes.) Add food coloring if desired.",
                    "Pipe your filling onto the back of half the shells. Form a sandwich and repeat. Macarons should be aged in the fridge for 1-3 days for best results. This allows the filling to soften the shells inside."
                )
            )

            "Homemade Ramen" -> Recipe(
                ingredients = listOf(
                    "1 tablespoon sesame oil",
                    "3 teaspoons grated ginger",
                    "4 teaspoons grated garlic",
                    "4 cups broth (I used chicken, but vegetable would also work)",
                    "4 cups water",
                    "1 ounce dried shiitake mushrooms",
                    "2 packages instant ramen (noodles only!)",
                    "1/2 cup chopped scallions or chives",
                    "2 cup chopped kale (or spinach)",
                    "1 cups shredded carrots (or another vegetable like bok choy)",
                    "Sriracha to taste",
                    "crunchy golden panko crumbs for topping"
                ),
                steps = listOf(
                    "Heat the sesame oil in a large skillet over medium low heat. Add the garlic and ginger; stir fry for 2 minutes or until soft and fragrant.",
                    "Add the broth and the water. Bring to a simmer; add the mushrooms and simmer for 10 minutes or until the mushrooms have softened and the broth is flavorful.",
                    "Add the instant noodles to the hot liquid and simmer for an additional 5 minutes or until the noodles have softened. Add the scallions and stir to combine.",
                    "Remove from heat, stir in the kale and carrots, and top with crunchy panko crumbs (see notes) and a soft-boiled egg (optional). Season with chili oil, hot sauce, sesame oil, and/or soy sauce and salt to taste."
                )
            )

            "Iced Coffee" -> Recipe(
                ingredients = listOf("Handful of ice cubes", "2 espresso shots", "1/2 cup milk"),
                steps = listOf(
                    "Brew espresso shots using an espresso maker.",
                    "Add ice to a large glass, then pour espresso shots and milk over top.",
                    "Add in 1-2 tbsp syrup of your choice. Serve and enjoy!"
                )
            )

            "Lasagna" -> Recipe(
                ingredients = listOf(
                    "2 tablespoons olive oil",
                    "1 large yellow onion, finely chopped",
                    "4 cloves garlic, finely chopped",
                    "6 anchovy fillets, optional",
                    "Kosher salt and freshly ground black pepper",
                    "2 tablespoons tomato paste, optional",
                    "1 28-ounce can whole peeled tomatoes",
                    "1 28-ounce can crushed tomatoes",
                    "For the assembly:",
                    "1½ pounds fresh mozzarella, grated or shredded",
                    "16 ounces (2 cups) whole-milk ricotta",
                    "1 cup coarsely grated Parmesan, plus more",
                    "⅓ cup heavy cream",
                    "Kosher salt and freshly ground black pepper",
                    "1 garlic clove, finely grated",
                    "1 pound dried lasagna noodles (not the no-boil variety)",
                    "Olive oil, for drizzling"
                ),
                steps = listOf(
                    "Make the sauce. Heat the olive oil in a large, heavy-bottomed pot over medium heat. Add the onion, garlic and anchovies and season with salt and pepper. Cook, stirring occasionally, until the onion is totally softened and translucent (without letting it brown), 8–10 minutes. Add the tomato paste if using and continue to cook, stirring, until the tomato paste has turned a deeper brick red color, about 2 minutes.",
                    "Using your hands, crush the whole tomatoes into smaller, bite-sized pieces and add them and the crushed tomatoes to the pot, stirring to scrape up any bits from the bottom. Fill one of the tomato cans halfway with water, swirl it around to get all the bits in the can and add it to the pot. Season with salt and pepper. Bring to a simmer and cook, stirring occasionally, until the tomato sauce has thickened and flavors have come together, 35–45 minutes. You want it to be as thick as tomato sauce from a jar — any looser and the lasagna will be too wet to cut into nice pieces.",
                    "Preheat the oven to 425°F and set a large pot of salted water to boil.",
                    "Assemble the lasagna. Set aside 1 cup mozzarella. In a medium bowl, combine the remaining mozzarella, the ricotta, Parmesan, cream, and grated garlic clove; season with salt and pepper.",
                    "Cook the lasagna noodles in the boiling water until just softened (before al dente), 4 to 5 minutes. Drain and separate any noodles that are trying to stick together, drizzling them with a bit of olive oil to prevent them from sticking further.",
                    "Spoon a bit of sauce on the bottom of a 3-quart baking dish and top with a layer of noodles, avoiding any heavy overlap (some overlap is fine and inevitable). Top with about 1¼ cups of sauce and dollop one-fourth of the cheese mixture over. Top with another layer of noodles and repeat three more times, ending with the last of the noodles (depending on size of the noodle/shape of the baking dish, you may have a few extra noodles) and the last of the sauce. Top with the reserved 1 cup mozzarella and more Parmesan, if you like.",
                    "Cover loosely with aluminum foil and place the baking dish on a foil-lined rimmed baking sheet (to prevent any overflow from burning on the bottom of your oven). Bake until the pasta is completely tender and cooked through and the sauce is bubbling up around the edges, 25–30 minutes. Remove the foil and continue to bake until the lasagna is golden brown on top with frilly, crispy edges and corners, another 35–45 minutes. Let cool slightly before eating.",
                )
            )
            "Mongolian Beef" -> Recipe(
                ingredients = listOf(
                    "2 teaspoons vegetable oil plus 2 tablespoons, divided",
                    "½ teaspoon minced fresh ginger",
                    "4 cloves garlic, finely minced",
                    "½ cup less sodium soy sauce",
                    "¼ cup water",
                    "½ cup brown sugar, packed",
                    "1 pound flank steak or your favorite cut of beef, thinly sliced",
                    "⅓ cup cornstarch",
                    "2 green onions, sliced"
                ),
                steps = listOf(
                    "Slice the flank steak into thin ¼ pieces. Toss with cornstarch, shaking off any excess and set aside.",
                    "In a 10-inch skillet, heat 2 teaspoons of oil over medium-low heat. Stir in minced ginger and garlic and cook until fragrant, about one minute.",
                    "Add soy sauce, water, and brown sugar to the skillet, then bring to a boil. Let it boil for 3-5 minutes until slightly thickened. Transfer to a small bowl and set aside.",
                    "Heat 1 tablespoon of oil in a separate pan or wok over medium-high heat. Cook the beef in small batches for about 2 minutes. It does not need to be cooked through.",
                    "Once all of the beef is browned, add the beef and sauce back to the skillet and heat over medium until hot and bubbly.",
                    "Remove from heat and stir in green onions. Serve over rice."
                )
            )
            "Cinnamon Rolls" -> Recipe(
                ingredients = listOf(
                    "2 and 3/4 cups (344g) all-purpose flour (spooned & leveled), plus more as needed",
                    "1/4 cup (50g) granulated sugar",
                    "1/2 teaspoon salt",
                    "3/4 cup (180ml) whole milk",
                    "3 Tablespoons (43g) unsalted butter",
                    "2 and 1/4 teaspoons instant yeast (1 standard packet)",
                    "1 large egg, at room temperature",
                    "3 Tablespoons (43g) unsalted butter, extra softened",
                    "1/3 cup (67g) packed light or dark brown sugar",
                    "1 Tablespoon ground cinnamon",
                    "4 ounces (113g) full-fat block cream cheese, softened to room temperature",
                    "2 Tablespoons (28g) unsalted butter, softened to room temperature",
                    "2/3 cup (80g) confectioners’ sugar",
                    "1 teaspoon pure vanilla extract"
                ),
                steps = listOf(
                    "Make the dough: Whisk the flour, sugar, and salt together in a large bowl. Set aside.",
                    "Combine the milk and butter together in a heatproof bowl. Microwave or use the stove and heat until the butter has melted and the mixture is warm to the touch (about 110°F/43°C, no higher).",
                    "Whisk in the yeast until it has dissolved. Pour mixture into the dry ingredients, add the egg, and stir with a sturdy rubber spatula or wooden spoon OR use a stand mixer with a paddle attachment on medium speed. Mix until a soft dough forms.",
                    "Transfer dough to a lightly floured surface. Using floured hands, knead the dough for 3-5 minutes until smooth. Add a little more flour if the dough is too sticky.",
                    "Place in a lightly greased bowl, cover loosely, and let rest for about 10 minutes while you prepare the filling.",

                    "Fill the rolls: Roll the dough out into a 14×8-inch (36×20-cm) rectangle. Spread softened butter on top.",
                    "Mix together the cinnamon and brown sugar. Sprinkle evenly over the dough.",
                    "Roll up the dough to make a 14-inch log. Cut into 10–12 even rolls and arrange in a lightly greased baking pan (round, pie, or square).",

                    "Rise: Cover the pan and let the rolls rise in a warm environment for 60–90 minutes or until doubled in size.",

                    "Bake the rolls: Preheat oven to 375°F (190°C). Bake for 24–27 minutes or until lightly browned.",
                    "If the tops brown too quickly, tent with foil and continue baking. Internal temperature should be around 195–200°F (91–93°C) when done.",
                    "Remove from oven and cool on a wire rack while making the icing.",

                    "Make the icing: Beat cream cheese on high speed until smooth. Add butter and beat until smooth and combined.",
                    "Add confectioners’ sugar and vanilla; beat until combined.",
                    "Spread icing over warm rolls and serve immediately.",
                )
            )
            "Pancake" -> Recipe(
                ingredients = listOf(
                    "1½ cups all-purpose flour, spooned and leveled",
                    "2 tablespoons cane sugar",
                    "1 tablespoon baking powder",
                    "½ teaspoon sea salt",
                    "1¼ cups milk, plus more as needed",
                    "1 large egg",
                    "3 tablespoons unsalted butter, melted and cooled slightly, or neutral oil, plus more for the pan",
                    "Maple syrup, for serving"
                ),
                steps = listOf(
                    "In a large bowl, whisk together the flour, sugar, baking powder, and salt.",
                    "In a medium bowl, whisk together the milk, egg, and melted butter.",
                    "Add the wet ingredients to the dry ingredients and mix until just combined. A few lumps are ok. If the batter is very thick, add milk, 1 tablespoon at a time, to loosen it slightly.",
                    "Heat a nonstick skillet over medium-low heat and brush lightly with butter or oil.",
                    "Use a ⅓-cup scoop to pour the batter into the skillet.",
                    "Cook for 1 to 2 minutes per side, or until the pancakes are puffed, golden brown, and cooked through in the middle.",
                    "Turn the heat to low as needed so that the middles cook through without burning the outsides.",
                    "Serve with maple syrup."
                )
            )


            else -> Recipe(
                ingredients = listOf("Ingredient 1", "Ingredient 2"),
                steps = listOf("Step 1", "Step 2")
            )
        }
    }

    private fun getRecipeImage(name: String?): Int {
        return when (name) {
            "Iced Matcha Latte" -> R.drawable.icedmatchalatte
            "Creamy Garlic Chicken" -> R.drawable.creamygarlicchicken
            "Filipino Chicken Adobo" -> R.drawable.filipinochickenadobo
            "French Macarons" -> R.drawable.frenchmacarons
            "Homemade Ramen" -> R.drawable.homemaderamen
            "Iced Coffee" -> R.drawable.icedcoffee
            "Lasagna" -> R.drawable.lasagna
            "Mongolian Beef" -> R.drawable.mongolianbeef
            "Cinnamon Rolls" -> R.drawable.cinnamonrolls
            "Pancake" -> R.drawable.pancake
            else -> R.drawable.icedmatchalatte
        }
    }


}