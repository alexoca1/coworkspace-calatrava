function FooterCowork() {
  return (
    <footer className="bg-superficie border-t border-divisor text-sm leading-relaxed p-6 mt-8">
      <div className="grid grid-cols-1 md:grid-cols-3 gap-8 max-w-7xl mx-auto">
        <div className="flex flex-col items-start gap-4 p-2">
          <p className="text-lg font-bold text-slate-principal">
            CoWorkSpace Calatrava
          </p>
          <p>
            El centro de coworking e innovación digital de referencia en Ciudad
            Real. Espacios flexibles, tecnología de vanguardia y comunidad para
            profesionales y empresas.
          </p>
          <p>Redes sociales</p>
          <div className="flex items-center gap-4 mt-2">
            <a
              href="https://www.linkedin.com"
              target="_blank"
              rel="noreferrer"
              aria-label="LinkedIn de CoWorkSpace"
              className="text-slate-500 hover:text-corporativo transition-colors"
            >
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeWidth="2"
                strokeLinecap="round"
                strokeLinejoin="round"
              >
                <path d="M16 8a6 6 0 0 1 6 6v7h-4v-7a2 2 0 0 0-2-2 2 2 0 0 0-2 2v7h-4v-7a6 6 0 0 1 6-6z" />
                <rect width="4" height="12" x="2" y="9" />
                <circle cx="4" cy="4" r="2" />
              </svg>
            </a>

            {/* Instagram */}
            <a
              href="https://www.instagram.com"
              target="_blank"
              rel="noreferrer"
              aria-label="Instagram de CoWorkSpace"
              className="text-slate-500 hover:text-corporativo transition-colors"
            >
              <svg
                width="20"
                height="20"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeWidth="2"
                strokeLinecap="round"
                strokeLinejoin="round"
              >
                <rect width="20" height="20" x="2" y="2" rx="5" ry="5" />
                <path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z" />
                <line x1="17.5" x2="17.51" y1="6.5" y2="6.5" />
              </svg>
            </a>

            {/* X / Twitter */}
            <a
              href="https://x.com"
              target="_blank"
              rel="noreferrer"
              aria-label="X (Twitter) de CoWorkSpace"
              className="text-slate-500 hover:text-corporativo transition-colors"
            >
              <svg
                width="18"
                height="18"
                viewBox="0 0 24 24"
                fill="currentColor"
              >
                <path d="M18.901 1.153h3.68l-8.04 9.19L24 22.846h-7.406l-5.8-7.584-6.638 7.584H.474l8.6-9.83L0 1.154h7.594l5.243 6.932ZM17.61 20.644h2.039L6.486 3.24H4.298Z" />
              </svg>
            </a>
          </div>
        </div>

        <div className="flex flex-col items-start gap-2 p-2 font-semibold text-slate-700">
          <p className="text-lg font-bold text-slate-principal">Espacios y Planes</p>
          <p>
            <a
              className="hover:text-corporativo hover:underline transition-colors"
              href="/..."
            >
              Puestos Flexibles (Hot Desk)
            </a>
          </p>
          <p>
            <a
              className="hover:text-corporativo hover:underline transition-colors"
              href="/..."
            >
              Puestos Fijos Dedicados
            </a>
          </p>
          <p>
            <a
              className="hover:text-corporativo hover:underline transition-colors"
              href="/..."
            >
              Cabinas Insonorizadas (Zoom Rooms)
            </a>
          </p>
          <p>
            <a
              className="hover:text-corporativo hover:underline transition-colors"
              href="/..."
            >
              Salas de Reuniones Corporativas
            </a>
          </p>
          <p>
            <a
              className="hover:text-corporativo hover:underline transition-colors"
              href="/..."
            >
              Comparativa de Membresías (FREE, FLEX, PRO)
            </a>
          </p>
        </div>

        <div className="flex flex-col items-start gap-2 p-2 font-semibold text-slate-700">
          <p className="text-lg font-bold text-principal">Dónde Estamos</p>
          <p>Dirección: Paseo de la Universidad, 1, 13005 Ciudad Real</p>
          <p>Teléfono: +34 926 22 00 00</p>
          <p className="break-all">Email: contacto@coworkspace-calatrava.es</p>
          <p>Horario de recepción:</p>
          <p>Lunes a Viernes: 08:00 - 20:00</p>
          <p>Sábados: 09:00 - 14:00</p>
          <p>Acceso Miembros PRO: 24/7</p>
        </div>
      </div>
      <div className="border-t border-divisor mt-6 pt-4 max-w-7xl mx-auto flex flex-col md:flex-row justify-between items-center gap-4 text-center md:text-left text-xs text-slate-500">
        <p>
          © 2026 CoWorkSpace Calatrava. Proyecto Fin de Ciclo DAW — IES Maestre
          de Calatrava.
        </p>
        <div className="flex flex-wrap justify-center gap-4 sm:gap-6">
          <a
            className="hover:text-corporativo hover:underline transition-colors"
            href="#aviso-legal"
          >
            Aviso Legal
          </a>
          <a
            className="hover:text-corporativo hover:underline transition-colors"
            href="#privacidad"
          >
            Política de Privacidad
          </a>
          <a
            className="hover:text-corporativo hover:underline transition-colors"
            href="#cookies"
          >
            Política de Cookies
          </a>
        </div>
      </div>
    </footer>
  );
}

export default FooterCowork;
