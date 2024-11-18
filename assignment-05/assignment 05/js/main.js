// Lazy loading example for images (if needed)
document.addEventListener("DOMContentLoaded", function() {
    const lazyImages = document.querySelectorAll("img");
    lazyImages.forEach(img => {
        img.loading = "lazy";
    });
});
