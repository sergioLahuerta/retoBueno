async function init() {
    try {
        let items = await getItems(); // ✅ Solo una llamada a getItems
        console.log('✅ items cargados:', items);

        let node = document.getElementById('results');

        for (let item of items) {
            let itemNode = document.createElement('div');
            itemNode.textContent = item.name;
            itemNode.style.color = 'white'; // ← estilo en línea directamente
            node.appendChild(itemNode);
        }
    } catch (err) {
        console.error('❌ Error cargando items:', err);
    }
}
