// === FUNCIONES GLOBALES DEL CARRITO ===
function obtenerCarrito() {
  const carritoJSON = localStorage.getItem('carrito');
  return carritoJSON ? JSON.parse(carritoJSON) : [];
}

function guardarCarrito(carrito) {
  localStorage.setItem('carrito', JSON.stringify(carrito));
}

function agregarAlCarrito(producto) {
  let carrito = obtenerCarrito();
  const index = carrito.findIndex(item => item.id_producto === producto.id_producto);
  if (index !== -1) {
    carrito[index].cantidad += producto.cantidad;
  } else {
    carrito.push(producto);
  }
  guardarCarrito(carrito);
}

function calcularTotal() {
  const carrito = obtenerCarrito();
  return carrito.reduce((total, item) => total + item.precio * item.cantidad, 0);
}

function modificarCantidad(id_producto, cambio) {
  let carrito = obtenerCarrito();
  const index = carrito.findIndex(p => p.id_producto === id_producto);

  if (index !== -1) {
    carrito[index].cantidad += cambio;

    if (carrito[index].cantidad <= 0) {
      carrito.splice(index, 1); // elimina producto si cantidad es 0
    }

    guardarCarrito(carrito);
    actualizarCarritoDOM();
  }
}

// Pintar el carrito con funcionalidad de las imagenes de los iconos mas y menos para que respectivamente aumente y reduzca la cantidad del producto deseado.
function actualizarCarritoDOM() {
  const carrito = obtenerCarrito();
  const listaCarrito = document.querySelector('.cart-items-list');
  const totalSpan = document.querySelector('.cart-total span:last-child');
  const contador = document.querySelector('.cart-count');

  if (listaCarrito && totalSpan) {
    listaCarrito.innerHTML = '';

    carrito.forEach(item => {
      const li = document.createElement('li');
      li.className = 'cart-item';
      li.dataset.idProducto = item.id_producto;

      li.innerHTML = `
        <span>${item.nombre} x${item.cantidad}</span>
        <img src="../src/icons/menos.png" alt="Restar" class="cart-img-icons icon-menos">
        <img src="../src/icons/mas.png" alt="Sumar" class="cart-img-icons icon-mas">
        <span>€${(item.precio * item.cantidad).toFixed(2)}</span>
      `;

      listaCarrito.appendChild(li);
    });

    
    document.querySelectorAll('.icon-menos').forEach(btn => {
      btn.addEventListener('click', (e) => {
        const li = e.target.closest('li.cart-item');
        const id = parseInt(li.dataset.idProducto);
        modificarCantidad(id, -1);
      });
    });

    document.querySelectorAll('.icon-mas').forEach(btn => {
      btn.addEventListener('click', (e) => {
        const li = e.target.closest('li.cart-item');
        const id = parseInt(li.dataset.idProducto);
        modificarCantidad(id, 1);
      });
    });

    totalSpan.innerText = `€${calcularTotal().toFixed(2)}`;
  }

  if (contador) {
    contador.textContent = carrito.length;
  }
}

function getIdFromUrl() {
  const params = new URLSearchParams(window.location.search);
  return params.get('id');
}

function cerrarPopupIngredientes() {
  const panel = document.getElementById('ingredient-choice-panel');
  const popup = panel?.closest('.details-card-container');
  if (popup) popup.classList.add('hidden');
}

// === LÓGICA DOM ===
document.addEventListener('DOMContentLoaded', () => {
  actualizarCarritoDOM();

  const btnNormal = document.getElementById('btn-normal');
  if (btnNormal) {
    btnNormal.addEventListener('click', () => {
      const nombre = document.querySelector('.details-name-product').innerText.trim();
      const precioText = document.querySelector('.details-price').innerText.trim();
      const precio = parseFloat(precioText.replace(/[^\d.,]/g, '').replace(',', '.')) || 0;
      const id = getIdFromUrl();

      if (!nombre || !precio || !id) {
        console.error('No se pudo obtener nombre, precio o ID del producto');
        return;
      }

      const producto = {
        id_producto: Number(id),
        nombre,
        precio,
        cantidad: 1
      };

      agregarAlCarrito(producto);
      actualizarCarritoDOM();
      cerrarPopupIngredientes();
    });
  }

  const checkoutBtn = document.querySelector('.cart-checkout-btn');
  if (checkoutBtn) {
    checkoutBtn.addEventListener('click', async () => {
      const carrito = obtenerCarrito();

      if (carrito.length === 0) {
        alert("El carrito está vacío.");
        return;
      }

      const nombre = prompt("Introduce tu nombre:");
      const email = prompt("Introduce tu email:");
      const metodoPago = prompt("Método de pago (TARJETA o EFECTIVO):");
      const id_restaurante = 1;

      if (!nombre || !email || !metodoPago) {
        alert("Por favor completa todos los datos.");
        return;
      }

      const productosFormateados = carrito.map(item => ({
        id: item.id_producto,
        cantidad: item.cantidad,
        observaciones: item.observaciones || ""
      }));

      const payload = {
        nombre,
        email,
        id_restaurante,
        metodoPago,
        productos: productosFormateados
      };

      try {
        const res = await fetch("http://localhost:8080/pruebaDBConsola/realizar-pedido", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(payload)
        });

        const text = await res.text();

        if (res.ok) {
          alert("Pedido realizado con éxito.");
          localStorage.removeItem('carrito');
          actualizarCarritoDOM();
        } else {
          alert("Error: " + text);
        }

      } catch (err) {
        console.error("Error en la conexión:", err);
        alert("No se pudo conectar con el servidor.");
      }
    });
  }
});
