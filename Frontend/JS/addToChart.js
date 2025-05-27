// ============================
// FUNCIONES DE GESTIÓN CARRITO
// ============================
function obtenerCarrito() {
  try {
    const carritoJSON = localStorage.getItem('carrito');
    return carritoJSON ? JSON.parse(carritoJSON) : [];
  } catch (error) {
    console.error('Error al obtener el carrito:', error);
    return [];
  }
}

function guardarCarrito(carrito) {
  try {
    localStorage.setItem('carrito', JSON.stringify(carrito));
  } catch (error) {
    console.error('Error al guardar el carrito:', error);
  }
}

function agregarAlCarrito(producto) {
  const carrito = obtenerCarrito();
  const index = carrito.findIndex(
    item => item.id_producto === producto.id_producto && item.observaciones === producto.observaciones
  );

  if (index !== -1) {
    carrito[index].cantidad += producto.cantidad;
  } else {
    carrito.push(producto);
  }

  guardarCarrito(carrito);
}

function modificarCantidad(id_producto, cambio, observaciones = '') {
  const carrito = obtenerCarrito();
  const index = carrito.findIndex(
    item => item.id_producto === id_producto && (item.observaciones || '') === observaciones
  );

  if (index !== -1) {
    carrito[index].cantidad += cambio;
    if (carrito[index].cantidad <= 0) carrito.splice(index, 1);
    guardarCarrito(carrito);
    actualizarCarritoDOM();
  }
}

function calcularTotal() {
  const carrito = obtenerCarrito();
  return carrito.reduce((total, item) => total + item.precio * item.cantidad, 0);
}

function getIdFromUrl() {
  return new URLSearchParams(window.location.search).get('id');
}

// ============================
// FUNCIONES DE UI
// ============================
function cerrarPopupIngredientes() {
  const panel = document.getElementById('ingredient-choice-panel');
  if (panel) panel.style.display = 'none';
}

function actualizarCarritoDOM() {
  const carrito = obtenerCarrito();
  const lista = document.querySelector('.cart-items-list');
  const totalSpan = document.querySelector('.cart-total span:last-child');
  const contador = document.querySelector('.cart-count');

  if (!lista || !totalSpan) return;

  lista.innerHTML = '';

  carrito.forEach(item => {
    const li = document.createElement('li');
    li.className = 'cart-item';
    li.dataset.idProducto = item.id_producto;
    li.dataset.observaciones = item.observaciones || '';

    li.innerHTML = `
      <span>${item.nombre} x${item.cantidad}${item.observaciones ? ` (${item.observaciones})` : ''}</span>
      <div class="cart-img-icons-container">
        <img src="../src/icons/menos.png" alt="Restar" class="cart-img-icons icon-menos">
        <img src="../src/icons/mas.png" alt="Sumar" class="cart-img-icons icon-mas">
      </div>
      <span>€${(item.precio * item.cantidad).toFixed(2)}</span>
    `;

    lista.appendChild(li);
  });

  // Delegación de eventos
  document.querySelectorAll('.icon-menos').forEach(btn => {
    btn.onclick = e => {
      const li = e.target.closest('li.cart-item');
      modificarCantidad(parseInt(li.dataset.idProducto), -1, li.dataset.observaciones || '');
    };
  });

  document.querySelectorAll('.icon-mas').forEach(btn => {
    btn.onclick = e => {
      const li = e.target.closest('li.cart-item');
      modificarCantidad(parseInt(li.dataset.idProducto), 1, li.dataset.observaciones || '');
    };
  });

  totalSpan.innerText = `€${calcularTotal().toFixed(2)}`;
  if (contador) contador.textContent = carrito.length;
}

// ============================
// EVENTOS AL CARGAR LA PÁGINA
// ============================
document.addEventListener('DOMContentLoaded', () => {
  actualizarCarritoDOM();

  // Abrir panel de ingredientes
  const btnMostrarIngredientes = document.querySelector('.details-button-purchase');
  if (btnMostrarIngredientes) {
    btnMostrarIngredientes.onclick = () => {
      const panel = document.getElementById('ingredient-choice-panel');
      if (panel) panel.style.display = 'flex';
    };
  }

  // Botón normal sin ingredientes
  const btnNormal = document.getElementById('btn-normal');
  if (btnNormal) {
    btnNormal.onclick = () => {
      const nombre = document.querySelector('.details-name-product')?.innerText.trim();
      const precioText = document.querySelector('.details-price')?.innerText.trim();
      const precio = parseFloat(precioText.replace(/[^\d.,]/g, '').replace(',', '.')) || 0;
      const id = getIdFromUrl();

      if (!nombre || !precio || !id) return console.error('Datos inválidos del producto.');

      agregarAlCarrito({ id_producto: Number(id), nombre, precio, cantidad: 1 });
      actualizarCarritoDOM();
      cerrarPopupIngredientes();
    };
  }

  // Botón aceptar con ingredientes personalizados
  document.addEventListener('click', (e) => {
    if (e.target.id === 'btn-accept-ingredients') {
      const nombre = document.querySelector('.details-name-product')?.innerText.trim();
      const precioText = document.querySelector('.details-price')?.innerText.trim();
      const precio = parseFloat(precioText.replace(/[^\d.,]/g, '').replace(',', '.')) || 0;
      const id = getIdFromUrl();

      if (!nombre || !precio || !id) return console.error('Faltan datos del producto');

      const noIngredientes = Array.from(document.querySelectorAll('.ingredient-checkbox input[type="checkbox"]'))
        .filter(input => input.checked)
        .map(input => input.value.trim());

      const observaciones = noIngredientes.length > 0 ? `with no ${noIngredientes.join(', ')}` : '';

      agregarAlCarrito({
        id_producto: Number(id),
        nombre,
        precio,
        cantidad: 1,
        observaciones
      });

      actualizarCarritoDOM();
      cerrarPopupIngredientes();
      const ingBox = document.querySelector('.ingredient-options');
      if (ingBox) ingBox.style.display = 'none';

      const selectIngText = document.getElementById('ingredients-list-panel-p');
      if (selectIngText) selectIngText.style.display = 'none';
      }
  });

  // ============================
  // ENVÍO DEL PEDIDO
  // ============================
  const checkoutBtn = document.querySelector('.cart-checkout-btn');
  if (checkoutBtn) {
    checkoutBtn.onclick = async () => {
      const carrito = obtenerCarrito();
      if (carrito.length === 0) return alert("El carrito está vacío.");

      const nombre = prompt("Introduce tu nombre:");
      const email = prompt("Introduce tu email:");
      const metodoPago = prompt("Método de pago (TARJETA o EFECTIVO):");

      if (!nombre || !email || !metodoPago) {
        return alert("Por favor completa todos los datos.");
      }

      const productosFormateados = carrito.map(item => ({
        ID_Producto: item.id_producto,
        cantidad: item.cantidad,
        observaciones: item.observaciones || ""
      }));

      const payload = {
        nombre: nombre,
        email: email,
        id_restaurante: 1,
        metodoPago: metodoPago,
        productos: productosFormateados
      };
      console.log("Payload enviado:", JSON.stringify(payload, null, 2));


      try {
        const res = await fetch("http://3.232.93.217:8080/realizar-pedido", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload)
        });

        const text = await res.text();
        if (res.ok) {
          alert("Pedido realizado con éxito.");
          localStorage.removeItem('carrito');
          actualizarCarritoDOM();
        } else {
          console.error("Respuesta del servidor:", text);
          alert("Error: " + text);
        }

      } catch (err) {
        console.error("Error de red:", err);
        alert("No se pudo conectar con el servidor.");
      }
    };
  }
});
