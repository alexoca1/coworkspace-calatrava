import BotonCowork from "./Boton";

function HeaderCowork() {
  return (
    <header className="bg-superficie border-b border-divisor ">
      <div className="max-w-7xl mx-auto py-4 px-6 flex flex-col sm:flex-row justify-between items-center gap-4">
        <div className="flex items-center gap-3.5">
          <svg
            width="60"
            height="60"
            viewBox="0 0 40 40"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
            className="shrink-0 w-11 h-11 sm:w-[60px] [60px]sm:h-[60px]"
          >
            {/* Icono de Edificio Colaborativo */}
            <path d="M16 8L6 14V34H16V8Z" fill="#0284c7" />
            <path d="M16 8L26 14V34H16V8Z" fill="#0369a1" />
            <path d="M26 18L32 21.5V34H26V18Z" fill="#cbd5e1" />

            {/* Ventanas */}
            <rect x="9" y="17" width="3" height="4" fill="white" />
            <rect x="9" y="25" width="3" height="4" fill="white" />
            <rect x="19" y="13" width="3" height="4" fill="white" />
            <rect x="19" y="21" width="3" height="4" fill="white" />
            <rect x="19" y="29" width="3" height="4" fill="white" />

            {/* Nodos de Conectividad (Red Digital) */}
            <circle cx="31" cy="11" r="3" fill="#0284c7" />
            <circle cx="35" cy="22" r="2.5" fill="#1e293b" />
            <line
              x1="26"
              y1="14"
              x2="28.5"
              y2="12"
              stroke="#0284c7"
              strokeWidth="1.5"
            />
            <line
              x1="31"
              y1="14"
              x2="33.5"
              y2="19.5"
              stroke="#1e293b"
              strokeWidth="1"
            />
            <line
              x1="26"
              y1="21"
              x2="32.5"
              y2="22"
              stroke="#cbd5e1"
              strokeWidth="1.5"
            />

            {/* Base */}
            <rect x="3" y="34" width="34" height="2" rx="1" fill="#1e293b" />
          </svg>

          <div className="flex flex-col">
            <span className="text-2xl sm:text-3xl font-extrabold text-principal leading-tight tracking-tight">
              CoWork<span className="text-corporativo">Space</span>
            </span>
            <span className="text-xs sm:text-sm tracking-[0.25em] font-bold text-slate-500 uppercase mt-0.5">
              Calatrava
            </span>
          </div>
        </div>

        <div className="flex items-center gap-3">
          <BotonCowork variante="secundario">Iniciar Sesión</BotonCowork>
          <BotonCowork variante="primario">Registrar</BotonCowork>
        </div>
      </div>
      <div className="bg-principal text-white px-6">
        <nav className="max-w-7xl mx-auto flex flex-wrap justify-center sm:justify-start items-center gap-4 sm:gap-8 py-3 text-sm font-medium">
          <a
            className="corporativoy-400 font-bold border-b-2 border-sky-400 pb-0.5"
            href="./"
          >
            Home
          </a>
          <a
            className="text-divisor hover:text-sky-400 hover:underline transition-colors pb-0.5"
            href="./"
          >
            Mis Reservas
          </a>
          <a
            className="text-divisor hover:text-sky-400 hover:underline transition-colors pb-0.5"
            href="./"
          >
            Panel Administrador
          </a>
        </nav>
      </div>
    </header>
  );
}

export default HeaderCowork;
