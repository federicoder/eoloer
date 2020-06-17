import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TipoAllegatoDetailComponent } from 'app/entities/tipo-allegato/tipo-allegato-detail.component';
import { TipoAllegato } from 'app/shared/model/tipo-allegato.model';

describe('Component Tests', () => {
  describe('TipoAllegato Management Detail Component', () => {
    let comp: TipoAllegatoDetailComponent;
    let fixture: ComponentFixture<TipoAllegatoDetailComponent>;
    const route = ({ data: of({ tipoAllegato: new TipoAllegato(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TipoAllegatoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TipoAllegatoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoAllegatoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tipoAllegato on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoAllegato).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
