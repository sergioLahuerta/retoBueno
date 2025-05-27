//CARRUSEL
const carpetasPorCategoria = {
  1: 'burgers',
  2: 'drinks',
  3: 'sides',
  4: 'desserts'
};

async function cargarProductosPorCategorias() {
  const categorias = [1, 2, 3, 4];

  for (const categoriaId of categorias) {
    await cargarProductosPorCategoria(categoriaId);
  }
}

async function cargarProductosPorCategoria(categoriaId) {
  try {
    const response = await fetch(`http://3.232.93.217:8080/api/productos?categoria=${categoriaId}`);
    if (!response.ok) throw new Error(`Error al cargar productos categoría ${categoriaId}`);

    const productos = await response.json();
    console.log(productos);
    const contenedor = document.getElementById(`carrusel-categoria-${categoriaId}`);

    if (!contenedor) {
      console.warn(`No se encontró el contenedor para categoría ${categoriaId}`);
      return;
    }

    contenedor.innerHTML = ''; 

    const carrusel = document.createElement('div');
    carrusel.className = 'carrusel';

    const carpeta = carpetasPorCategoria[categoriaId] || 'default';

    productos.forEach((producto, index) => {
      const card = document.createElement('div');
      card.className = 'carousel-element-card';
      card.innerHTML = `
        <div class="carousel-card-content">
          <img class="carrusel-image" src="../src/products/burgers/${producto.id_producto}.png" alt="${producto.nombre}">
          <p class="carousel-card-burger-name">${producto.nombre}</p>
          <a href="details.html?id=${producto.id_producto}" class="carousel-button-a">
            <p class="carousel-button-p">Taste me!</p>
          </a>
        </div>
      `;
      carrusel.appendChild(card);
    });

    contenedor.appendChild(carrusel);

    const progressContainer = document.createElement('div');
    progressContainer.className = 'carousel-progress-container';
    progressContainer.innerHTML = `<div class="carousel-progress-bar"></div>`;
    contenedor.appendChild(progressContainer);

    inicializarCarrusel(contenedor);

  } catch (error) {
    console.error(error);
  }
}
cargarProductosPorCategorias();


//OFERTAS
async function cargarOfertas() {
  try {
    const response = await fetch('http://3.232.93.217:8080/api/ofertas');
    if (!response.ok) throw new Error('Error al cargar ofertas');

    const productos = await response.json();
    console.log('Productos recibidos para ofertas:', productos);

    const contenedor = document.getElementById('ofertas');
    if (!contenedor) {
      console.warn('Contenedor "ofertas" no encontrado, se omite la carga de ofertas.');
      return;
    }

    while (contenedor.children.length > 1) {
      contenedor.removeChild(contenedor.lastChild);
    }

    const ofertas = productos.filter(p => p.id_oferta > 0);
    if (ofertas.length === 0) {
      contenedor.innerHTML += '<p>No hay ofertas disponibles.</p>';
      return;
    }

    ofertas.forEach(oferta => {
      const imagen = oferta.columnaImagen;
      const url = '/HTML/';

      const card = document.createElement('div');
      card.className = 'card-promo-burger-ad';
      card.innerHTML = `
        <img src="${imagen}" alt="${oferta.nombre}" class="img-promo-burger">
        <div class="info-card-promo-burger">
          <p class="title-info-card-promo-burger">${oferta.nombre}</p>
          <p class="subtitle-info-card-promo-burger">${oferta.descripcion}</p>
          <button class="button-info-card-promo-burger" onclick="window.location.href='${url}'">
            Try it now!
          </button>
        </div>
      `;
      contenedor.appendChild(card);
    });

  } catch (error) {
    console.error('Error al traer ofertas:', error);
  }
}
cargarOfertas();

//Restaurantes
async function cargarRestaurantes() {
  try {
    const response = await fetch('http://3.232.93.217:8080/api/restaurantes');
    if (!response.ok) throw new Error('Error al cargar restaurantes');
    
    const restaurantes = await response.json();
    console.log('Restaurantes:', restaurantes);

    const contenedor = document.getElementById('restaurantes-container');
    if (!contenedor) {
      console.warn('Contenedor "restaurantes" no encontrado, se omite la carga de restaurantes.');
      return;
    }

    contenedor.innerHTML = '';

    restaurantes.forEach(restaurante => {
      const card = document.createElement('div');
      card.className = 'find-your-restaurant-card';

      card.innerHTML = `
        <img src="${restaurante.imagenRestaurante}" alt="${restaurante.nombre}" class="find-your-restaurant-img" />
        <div class="find-your-restaurant-card-info-container">
          <span class="find-your-restaurant-title">${restaurante.nombre.toUpperCase()}</span>
          <p class="find-your-restaurant-location">${restaurante.direccion}</p>
          <span class="find-your-restaurant-location-city">Los Angeles, United States</span>
          <a href="https://maps.app.goo.gl/Xos958UCV9sqQU6SA" target="_blank">
            <p class="find-your-restaurant-maps-link">See on maps</p>
          </a>
        </div>
        <div class="find-your-restaurant-below-bar-container">
          <button class="find-your-restaurant-below-bar-button">CALL NOW</button>
          <div class="call-now-hidden-pannel">
            <div class="call-now-number">${restaurante.telefono}</div>
            <div class="call-now-number copy-clipboard">Copied to clipboard!</div>
            <div class="call-now-below-options-container">
              <button class="call-now-below-option cancel">Cancel</button>
              <button class="call-now-below-option copy">Copy</button>
            </div>
          </div>
          <div class="find-your-restaurant-icons-container">
            <img src="../src/icons/cochecito-de-bebe.png" alt="" class="find-your-restaurant-icon-img" />
            <img src="../src/icons/sombrilla.png" alt="" class="find-your-restaurant-icon-img" />
            <img src="../src/icons/silla-de-ruedas.png" alt="" class="find-your-restaurant-icon-img" />
            <img src="../src/icons/wifi.png" alt="" class="find-your-restaurant-icon-img" />
          </div>
        </div>
      `;

      contenedor.appendChild(card);
      attachRestaurantEventListeners();
    });

  } catch (error) {
    console.error('Error al cargar restaurantes:', error);
  }
}
window.addEventListener('DOMContentLoaded', cargarRestaurantes);
cargarRestaurantes();

//DEATLLES
function getIdFromUrl() {
  const params = new URLSearchParams(window.location.search);
  const id = parseInt(params.get('id'));
  return isNaN(id) ? null : id;
}

async function cargarProductoDetalles() {
  const id = getIdFromUrl();

  if (!id) {
    console.error('No se recibió ID de producto');
    return;
  }

  try {
    const response = await fetch(`http://3.232.93.217:8080/api/productos/${id}`);
    if (!response.ok) throw new Error('Error al cargar producto');

    const producto = await response.json();
    mostrarProducto(producto);
  } catch (error) {
    console.error(error);
  }
}

function mostrarProducto(producto) {
  const img = document.querySelector('.details-img');
  const nombre = document.querySelector('.details-name-product');
  const subtext = document.querySelector('.details-subtext-product');
  const descripcion = document.querySelector('.details-description-product');
  const precio = document.querySelector('.details-price');

  if (img) {
    img.src = `../src/products/burgers/${producto.id_producto}.png`;
    img.alt = producto.nombre;
  }

  if (nombre) nombre.innerText = producto.nombre;
  if (subtext) subtext.innerText = '';
  if (descripcion) descripcion.innerText = producto.descripcion || 'Sin descripción';
  if (precio) precio.innerText = producto.precio ? `${producto.precio.toFixed(2)} €` : '';
}
cargarProductoDetalles();


//Ingredientes Details
async function cargarIngredientesDelProducto() {
  const productoId = getIdFromUrl();
  console.log("Producto ID:", productoId);

  if (!productoId) {
    console.error("No se encontró el ID del producto en la URL");
    return;
  }

  const url = `http://3.232.93.217:8080/api/ingredientes?producto=${productoId}`;

  try {
    const res = await fetch(url);
    if (!res.ok) throw new Error(`Error al cargar ingredientes: ${res.status} ${res.statusText}`);

    const ingredientes = await res.json();
    const container = document.querySelector(".ingredient-options");

    if (!container) {
      console.warn("No se encontró el contenedor .ingredient-options");
      return;
    }

    container.innerHTML = "";

    if (ingredientes.length === 0) {
      container.innerText = "No hay ingredientes disponibles para este producto.";
      return;
    }

    ingredientes.forEach(ing => {
      const label = document.createElement("label");
      label.className = "ingredient-checkbox";

      label.innerHTML = `
        <input type="checkbox" name="ingredient" value="${ing.nombre}">
        ${ing.nombre}
      `;

      container.appendChild(label);
    });

    const submitBtn = document.createElement("button");
    submitBtn.id = "btn-accept-ingredients";
    submitBtn.textContent = "Submit";
    submitBtn.classList.add("details-button-purchase");

container.appendChild(submitBtn);

  } catch (error) {
    console.error("Error al cargar ingredientes:", error);
  }
}
cargarIngredientesDelProducto();


//AUTORÍA//

//1
async function funcionAutoria() {
  try {
    const response = await fetch('http://3.232.93.217:8080/api/productos?categoria=1');
    if (!response.ok) throw new Error('Error con aleatorio');

    const productos = await response.json();
    console.log('Productos recibidos:', productos);

     function getRandomItem(productos) {
      return productos[Math.floor(Math.random() * productos.length)];
    }

    const contenedor = document.getElementById('autoria');
    if (!contenedor) {
      console.warn('Contenedor no encontrado, se omite la carga de productos aleatorios.');
      return;
    }

    const aleatorio = getRandomItem(productos);
    
      const card = document.createElement('div');
      card.className = 'card-product-container';

      card.innerHTML = `
        <img src="../src/products/burgers/1.png" alt="${aleatorio.nombre}" class="img-promo-burger">
        <div class="info-card-promo-burger">
          <p class="title-info-card-promo-burger">${aleatorio.nombre}</p>
          <p class="subtitle-info-card-promo-burger">${aleatorio.descripcion}</p>
          <button class="button-info-card-promo-burger" onclick="window.location.href='./details.html'">
            Try it now!
          </button>
        </div>
      `;
      contenedor.appendChild(card);

  } catch (error) {
    console.error('Error al traer ofertas:', error);
  }
}
funcionAutoria();



//2
async function funcionAutoria2() {
  try {
    const response = await fetch('http://3.232.93.217:8080/api/productos?categoria=2');
    if (!response.ok) throw new Error('Error con aleatorio');

    const productos2 = await response.json();
    console.log('Productos recibidos:', productos2);

     function getRandomItem(productos2) {
      return productos2[Math.floor(Math.random() * productos2.length)];
    }

    const contenedor2 = document.getElementById('autoria2');
    if (!contenedor2) {
      console.warn('Contenedor no encontrado, se omite la carga de productos aleatorios.');
      return;
    }

    const aleatorio2 = getRandomItem(productos2);
    
      const card2 = document.createElement('div');
      card2.className = 'card-product-container';

      card2.innerHTML = `
        <img src="../src/products/burgers/1.png" alt="${aleatorio2.nombre}" class="img-promo-burger">
        <div class="info-card-promo-burger">
          <p class="title-info-card-promo-burger">${aleatorio2.nombre}</p>
          <p class="subtitle-info-card-promo-burger">${aleatorio2.descripcion}</p>
          <button class="button-info-card-promo-burger" onclick="window.location.href='./details.html'">
            Try it now!
          </button>
        </div>
      `;
      contenedor2.appendChild(card2);

  } catch (error) {
    console.error('Error al traer ofertas:', error);
  }
}
funcionAutoria2();


//3
async function funcionAutoria3() {
  try {
    const response = await fetch('http://3.232.93.217:8080/api/productos?categoria=3');
    if (!response.ok) throw new Error('Error con aleatorio');

    const productos3 = await response.json();
    console.log('Productos recibidos:', productos3);

     function getRandomItem(productos3) {
      return productos3[Math.floor(Math.random() * productos3.length)];
    }

    const contenedor3 = document.getElementById('autoria3');
    if (!contenedor3) {
      console.warn('Contenedor no encontrado, se omite la carga de productos aleatorios.');
      return;
    }

    const aleatorio3 = getRandomItem(productos3);
    
      const card3 = document.createElement('div');
      card3.className = 'card-product-container';

      card3.innerHTML = `
        <img src="../src/products/burgers/1.png" alt="${aleatorio3.nombre}" class="img-promo-burger">
        <div class="info-card-promo-burger">
          <p class="title-info-card-promo-burger">${aleatorio3.nombre}</p>
          <p class="subtitle-info-card-promo-burger">${aleatorio3.descripcion}</p>
          <button class="button-info-card-promo-burger" onclick="window.location.href='./details.html'">
            Try it now!
          </button>
        </div>
      `;
      contenedor3.appendChild(card3);

  } catch (error) {
    console.error('Error al traer ofertas:', error);
  }
}
funcionAutoria3();