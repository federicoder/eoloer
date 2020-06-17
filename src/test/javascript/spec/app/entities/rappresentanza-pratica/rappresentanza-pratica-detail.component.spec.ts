import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { RappresentanzaPraticaDetailComponent } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica-detail.component';
import { RappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';

describe('Component Tests', () => {
  describe('RappresentanzaPratica Management Detail Component', () => {
    let comp: RappresentanzaPraticaDetailComponent;
    let fixture: ComponentFixture<RappresentanzaPraticaDetailComponent>;
    const route = ({ data: of({ rappresentanzaPratica: new RappresentanzaPratica(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RappresentanzaPraticaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RappresentanzaPraticaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RappresentanzaPraticaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rappresentanzaPratica on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rappresentanzaPratica).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
