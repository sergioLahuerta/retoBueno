async function getItems() {
    const response = await fetch('/sampleApi/mockup/items.json'); // <- Espera la respuesta
    const items = await response.json();                 // <- Espera el JSON
    return items;
}
