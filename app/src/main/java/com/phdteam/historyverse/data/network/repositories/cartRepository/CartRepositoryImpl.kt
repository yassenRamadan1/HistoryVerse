package com.phdteam.historyverse.data.network.repositories.cartRepository

import com.phdteam.historyverse.data.network.model.CartItem

class CartRepositoryImpl : CartRepository {
    override suspend fun getCartItems(): List<CartItem> {
        return fakeData
    }

    override suspend fun deleteItemFromCart(id: Int) {
        TODO("Not yet implemented")
    }

    private val fakeData = listOf(
        CartItem(
            id = 1,
            name = "Widget",
            price = 45.0,
            image = "https://www.boredpanda.com/blog/wp-content/uploads/2022/12/2509735068_1adc5ab69a_k-63a17b3acddd5__700.jpg"
        ),
        CartItem(
            id = 2,
            name = "Gadget",
            price = 19.49,
            image = "https://www.boredpanda.com/blog/wp-content/uploads/2022/12/CairoEgMuseumTaaMaskMostlyPhotographed-63a17b9299211__700.jpg"
        ),
        CartItem(
            id = 3,
            name = "Doodad",
            price = 75.20,
            image = "https://i.pinimg.com/564x/8b/e6/41/8be6410f519678b1206c03b77a78f539.jpg"
        ),
        CartItem(
            id = 4,
            name = "Thingamajig",
            price = 50.00,
            image = "https://ih1.redbubble.net/image.988510287.9131/fposter,small,wall_texture,square_product,600x600.jpg"
        ),
        CartItem(
            id = 5,
            name = "Doohickey",
            price = 30.75,
            image = "https://cdn11.bigcommerce.com/s-fme25/images/stencil/500x659/products/6729/102608/urn47jhhy__19172.1656637780.jpg?c=2"
        )
    )
}

