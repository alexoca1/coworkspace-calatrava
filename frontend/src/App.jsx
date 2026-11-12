import FooterCowork from "./components/Footer";
import HeaderCowork from "./components/Header";

export default function App() {
  return (
    <div className="min-h-screen bg-fondo flex flex-col justify-between">
      {/* Header */}
      <HeaderCowork />

      {/* Contenido central */}
      <main className="p-8 text-center flex-1">
        <h1 className="text-2xl font-bold text-principal">
          🏢 CoWorkSpace Calatrava
        </h1>
        <p className="text-slate-600 mt-2">Área de trabajo en desarrollo...</p>
      </main>

      {/* Footer */}
      <FooterCowork />
    </div>
  );
}