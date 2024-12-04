from producte import Producte

class Botiga:
    def __init__(self):
        # Inicializamos con algunos productos en estoc.
        self.estoc = [
            Producte("Poma", 1.0, 50),
            Producte("Plàtan", 0.8, 100),
            Producte("Taronja", 1.2, 75)
        ]
        self.cistella = []

    def mostrar_estoc(self):
        print("\nProductes disponibles:")
        for i, producte in enumerate(self.estoc, start=1):
            print(f"{i}. {producte.nom} - {producte.preu} €/unitat - {producte.quantitat} disponibles")

    def comprar_producte(self, index, quantitat):
        if 1 <= index <= len(self.estoc):
            producte = self.estoc[index - 1]
            if quantitat <= producte.quantitat:
                producte.quantitat -= quantitat
                self.cistella.append(Producte(producte.nom, producte.preu, quantitat))
                print(f"Has comprat {quantitat} unitats de {producte.nom}.")
            else:
                print(f"Només tenim {producte.quantitat} unitats disponibles.")
        else:
            print("Opció de producte no vàlida.")

    def mostrar_cistella(self):
        if not self.cistella:
            print("\nLa cistella està buida.")
        else:
            print("\nProductes a la cistella:")
            for producte in self.cistella:
                print(f"{producte.nom} - {producte.quantitat} unitats a {producte.preu} €/unitat")

    def generar_factura(self):
        if not self.cistella:
            print("\nNo tens cap producte a la cistella per generar la factura.")
        else:
            total = 0
            print("\n--- FACTURA ---")
            for producte in self.cistella:
                cost = producte.quantitat * producte.preu
                total += cost
                print(f"{producte.nom} - {producte.quantitat} unitats a {producte.preu} €/unitat")
            print(f"Total a pagar: {total:.2f} €")
            esborrar = input("Vols esborrar la cistella després de generar la factura? (s/n): ")
            if esborrar.lower() == 's':
                self.cistella.clear()
                print("Cistella esborrada. Pots continuar comprant.")
