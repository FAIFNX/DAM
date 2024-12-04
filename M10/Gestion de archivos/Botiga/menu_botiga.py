from botiga import Botiga

class MenuBotiga:
    def __init__(self):
        self.botiga = Botiga()

    def mostrar_menu(self):
        while True:
            print("\nOpcions del menú:")
            print("1- Comprar un producte")
            print("2- Mostrar la cistella de la compra")
            print("3- Generar factura")
            print("4- Sortir")

            opcio = input("Selecciona una opció (1-4): ")

            if opcio == "1":
                self.botiga.mostrar_estoc()
                try:
                    index = int(input("Selecciona el número del producte: "))
                    quantitat = int(input("Quantes unitats vols comprar? "))
                    self.botiga.comprar_producte(index, quantitat)
                except ValueError:
                    print("Entrada no vàlida.")
            elif opcio == "2":
                self.botiga.mostrar_cistella()
            elif opcio == "3":
                self.botiga.generar_factura()
            elif opcio == "4":
                print("Sortint del programa.")
                break
            else:
                print("Opció no vàlida. Si us plau, selecciona una opció correcta.")
