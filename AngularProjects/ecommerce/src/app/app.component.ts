import { Component } from '@angular/core';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { CommonModule } from '@angular/common';  // Import CommonModule

@Component({
  selector: 'app-root',
  standalone: true,  // Standalone component
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [CommonModule, CategoryListComponent, ProductListComponent],  // Import components directly
})
export class AppComponent {
  selectedCategory: string = 'Laptops';

  onCategorySelected(category: string) {
    this.selectedCategory = category;
  }
}
